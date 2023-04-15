package uhk.umte.financeplusv3.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.databinding.FragmentMainBinding
import uhk.umte.financeplusv3.models.Category
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.models.TransactionType
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import java.util.*
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class MainFragment : Fragment(){
    private lateinit var binding: FragmentMainBinding
    private val viewModel: TransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Nastavení OnClickListeneru pro tlačítko
        binding.addTransactionButton.setOnClickListener {
            showTransactionTypeSelectionDialog()
        }

        addInitialTransactionIfEmpty()

        // Nastavení observerů pro zobrazení dat ve fragmentu
        setupObservers()

        //Nastavení Pie Chart
        setupPieChart()

        return binding.root
    }

    private fun showAddTransactionBottomSheet(transactionType: TransactionType) {
        val bottomSheetDialogFragment = AddTransactionBottomSheetFragment.newInstance(transactionType)
        bottomSheetDialogFragment.show(childFragmentManager, AddTransactionBottomSheetFragment.TAG)
    }

    private fun addInitialTransactionIfEmpty() {
        lifecycleScope.launch {
            val hasAnyTransactions = viewModel.hasAnyTransactions()
            if (!hasAnyTransactions) {
                val initialTransaction = Transaction(
                    id = 0,
                    amount = 3.0,
                    category = Category.OTHER,
                    date = Date(System.currentTimeMillis()),
                    description = "První příjem",
                    transactionType = TransactionType.INCOME
                )

                val initialTransactionExpense = Transaction(
                    id = 0,
                    amount = 2.0,
                    category = Category.OTHER,
                    date = Date(System.currentTimeMillis()),
                    description = "První výdaj",
                    transactionType = TransactionType.EXPENSE
                )
                viewModel.insert(initialTransaction)
                viewModel.insert(initialTransactionExpense)

            }
        }
    }

    private fun showTransactionTypeSelectionDialog() {
        val options = arrayOf("Příjem", "Výdaj")
        AlertDialog.Builder(requireContext())
            .setTitle("Vyberte typ transakce")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showAddTransactionBottomSheet(TransactionType.INCOME)
                    1 -> showAddTransactionBottomSheet(TransactionType.EXPENSE)
                }
            }
            .show()
    }
    private fun setupObservers() {
        viewModel.totalIncomes.observe(viewLifecycleOwner, { total ->
            binding.totalIncomesTextView.text = total.toString()
        })

        viewModel.totalExpenses.observe(viewLifecycleOwner, { total ->
            binding.totalExpensesTextView.text = total.toString()
        })

        viewModel.totalIncomeCount.observe(viewLifecycleOwner, { count ->
            binding.incomeCountTextView.text = count.toString()
        })

        viewModel.totalExpenseCount.observe(viewLifecycleOwner, { count ->
            binding.expenseCountTextView.text = count.toString()
        })
    }

    private fun setupPieChart() {
        val pieChart = binding.pieChart

        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.setCenterText("Příjmy a výdaje")
        pieChart.setCenterTextSize(18f)

        updatePieChart()
    }

    private fun updatePieChart() {
        val pieChart = binding.pieChart

        lifecycleScope.launch {
            val totalIncomes = viewModel.totalIncomes.value ?:0.0
            val totalExpenses = viewModel.totalExpenses.value ?:0.0

            val entries = ArrayList<PieEntry>().apply {
                add(PieEntry(totalIncomes.toFloat(), "Příjmy"))
                add(PieEntry(totalExpenses.toFloat(), "Výdaje"))
            }

            val dataSet = PieDataSet(entries, "")
            dataSet.setColors(
                Color.GREEN,  // Barva pro příjmy
                Color.RED     // Barva pro výdaje
            )

            val pieData = PieData(dataSet).apply {
                setValueTextSize(16f)
            }

            pieChart.data = pieData
            pieChart.invalidate()
        }
    }
}