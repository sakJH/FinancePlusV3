package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uhk.umte.financeplusv3.adapters.TransactionAdapter
import uhk.umte.financeplusv3.databinding.FragmentExpenseBinding
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.models.TransactionType

class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Nastavení RecyclerView pro zobrazení všech výdajů
        val transactionAdapter = TransactionAdapter(object :
            TransactionAdapter.OnTransactionItemClickListener {
            override fun onTransactionItemClick(transaction: Transaction) {
                // Zde implementovat akci, která se provede po klepnutí na položku
                showTransactionDetailBottomSheet(transaction)
            }
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = transactionAdapter

        // Nastavení observeru pro LiveData objekt všech výdajů
        viewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            transactionAdapter.submitList(filterExpenseTransactions(transactions))
        }
    }

    private fun filterExpenseTransactions(transactions: List<Transaction>): List<Transaction> {
        return transactions.filter { it.transactionType == TransactionType.EXPENSE }
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