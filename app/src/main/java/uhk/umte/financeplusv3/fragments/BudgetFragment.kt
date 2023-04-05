package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.adapters.TransactionAdapter
import uhk.umte.financeplusv3.databinding.FragmentBudgetBinding
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel

class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val budgetAdapter = TransactionAdapter { transaction ->
            // Handle transaction item click
        }

        binding.recyclerView.apply {
            adapter = budgetAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            budgetAdapter.submitList(transactions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}