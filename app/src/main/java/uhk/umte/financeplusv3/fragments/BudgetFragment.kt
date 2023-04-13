package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uhk.umte.financeplusv3.adapters.TransactionAdapter
import uhk.umte.financeplusv3.databinding.FragmentBudgetBinding
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!

    // Získání instance ViewModelu pomocí Koin
    private val viewModel: TransactionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializace RecyclerView pro zobrazení všech transakcí
        val transactionRecyclerView = binding.transactionRecyclerView
        val transactionAdapter = TransactionAdapter { transaction ->
            // Zde implementovat akci, která se provede po klepnutí na položku
        }
        transactionRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        transactionRecyclerView.adapter = transactionAdapter

        // Nastavení observeru pro LiveData objekt všech transakcí
        viewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            transactionAdapter.submitList(transactions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}