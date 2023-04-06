package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import uhk.umte.financeplusv3.adapters.TransactionAdapter
import uhk.umte.financeplusv3.databinding.FragmentExpenseBinding
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel

class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val expenseAdapter = TransactionAdapter { transaction ->
            // Handle transaction item click
        }

        binding.recyclerView.apply {
            adapter = expenseAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        //TODO
        /*viewModel.allExpenses.observe(viewLifecycleOwner) { expenseTransactions ->
            expenseAdapter.submitList(expenseTransactions)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}