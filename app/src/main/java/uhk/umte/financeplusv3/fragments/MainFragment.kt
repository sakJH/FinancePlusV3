package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.databinding.FragmentMainBinding
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel

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
}