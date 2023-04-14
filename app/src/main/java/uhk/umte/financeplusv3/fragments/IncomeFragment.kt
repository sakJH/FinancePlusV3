package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uhk.umte.financeplusv3.adapters.CategoryTransactionAdapter
import uhk.umte.financeplusv3.adapters.TransactionAdapter
import uhk.umte.financeplusv3.databinding.FragmentIncomeBinding
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.models.TransactionType
import uhk.umte.financeplusv3.models.categoryitems.CategoryHeader
import uhk.umte.financeplusv3.models.categoryitems.CategoryItem
import uhk.umte.financeplusv3.models.categoryitems.TransactionItem

class IncomeFragment : Fragment() {

    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Nastavení RecyclerView pro zobrazení všech příjmů
        val onTransactionItemClickListener: (Transaction) -> Unit = { transaction ->
            showTransactionDetailBottomSheet(transaction)
        }

        val categoryTransactionAdapter = CategoryTransactionAdapter(onTransactionItemClickListener)
        binding.incomeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.incomeRecyclerView.adapter = categoryTransactionAdapter

        // Nastavení observeru pro LiveData objekt všech příjmů
        viewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            val filteredIncomeTransactions = filterIncomeTransactions(transactions)
            val categoryItems = createCategoryItems(filteredIncomeTransactions)
            categoryTransactionAdapter.submitList(categoryItems)
        }
    }

    private fun filterIncomeTransactions(transactions: List<Transaction>): List<Transaction> {
        return transactions.filter { it.transactionType == TransactionType.INCOME }
    }

    private fun createCategoryItems(transactions: List<Transaction>): List<CategoryItem> {
        val categoryItems = mutableListOf<CategoryItem>()

        // Group transactions by category
        val groupedTransactions = transactions.groupBy { it.category }

        // Create a CategoryItem list with headers and transaction items
        groupedTransactions.forEach { (category, transactions) ->
            categoryItems.add(CategoryHeader(category))
            transactions.forEach { transaction ->
                categoryItems.add(TransactionItem(transaction))
            }
        }

        return categoryItems
    }

    private fun showTransactionDetailBottomSheet(transaction: Transaction) {
        val transactionDetailFragment = TransactionDetailFragment.newInstance(transaction)
        transactionDetailFragment.show(childFragmentManager, TransactionDetailFragment.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}