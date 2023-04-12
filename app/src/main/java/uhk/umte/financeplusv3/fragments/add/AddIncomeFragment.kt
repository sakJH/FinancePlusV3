package uhk.umte.financeplusv3.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uhk.umte.financeplusv3.databinding.FragmentAddIncomeBinding
import androidx.navigation.fragment.findNavController
import uhk.umte.financeplusv3.R

class AddIncomeFragment : Fragment() {

    private var _binding: FragmentAddIncomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addIncomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_addIncomeFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
