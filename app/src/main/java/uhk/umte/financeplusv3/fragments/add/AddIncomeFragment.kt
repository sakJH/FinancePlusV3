package uhk.umte.financeplusv3.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import uhk.umte.financeplusv3.databinding.FragmentAddIncomeBinding
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.adapters.CategoryArrayAdapter
import uhk.umte.financeplusv3.fragments.AddTransactionBottomSheetFragment
import uhk.umte.financeplusv3.models.Category
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.models.TransactionType
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import java.util.*

class AddIncomeFragment : Fragment() {
    private lateinit var binding: FragmentAddIncomeBinding
    private val viewModel: TransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_income, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // Nastavení spinneru pro výběr kategorie
        val adapter = CategoryArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Category.values()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = adapter

        // Nastavení OnClickListeneru pro tlačítko
        binding.submitIncomeButton.setOnClickListener {
            saveIncomeToDatabase()
        }

        return binding.root
    }

    private fun saveIncomeToDatabase() {
        val description = binding.descriptionEditText.text.toString()
        val category = binding.categorySpinner.selectedItem as Category
        val amount = binding.amountEditText.text.toString().toDoubleOrNull()

        if (amount != null) {
            val newIncome = Transaction(
                id = 0,
                amount = amount,
                category = category,
                date = Date(System.currentTimeMillis()),
                description = description,
                transactionType = TransactionType.INCOME
            )
            viewModel.insert(newIncome)
            Toast.makeText(requireContext(), "Příjem byl úspěšně přidán", Toast.LENGTH_SHORT).show()
            try {
                (parentFragment as AddTransactionBottomSheetFragment).dismiss()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                requireActivity().onBackPressed()
            }
        } else {
            Toast.makeText(requireContext(), "Zadejte platnou částku", Toast.LENGTH_SHORT).show()
        }
    }
}
