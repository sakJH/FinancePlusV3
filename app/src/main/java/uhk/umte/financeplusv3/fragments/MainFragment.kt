package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.databinding.FragmentMainBinding
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import java.util.*

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: TransactionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Nastavení OnClickListeneru pro tlačítka
        setupButtonListeners()

        addInitialTransactionIfEmpty()

        return binding.root
    }

    private fun setupButtonListeners() {
        binding.addIncomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addIncomeFragment)
        }

        binding.addExpenseButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addExpenseFragment)
        }
    }

    private fun addInitialTransactionIfEmpty() {
        lifecycleScope.launch {
            val hasAnyTransactions = viewModel.hasAnyTransactions()
            if (!hasAnyTransactions) {
                val initialTransaction = Transaction(
                    id = 0,
                    amount = 1.0,
                    category = "Initial",
                    date = Date(System.currentTimeMillis()),
                    description = "První příjem",
                    transactionType = "income"
                )
                viewModel.insert(initialTransaction)
            }
        }
    }
}