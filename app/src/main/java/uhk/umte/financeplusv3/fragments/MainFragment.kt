package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.databinding.FragmentMainBinding
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addIncomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addIncomeFragment)
        }

        binding.addExpenseButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addExpenseFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}