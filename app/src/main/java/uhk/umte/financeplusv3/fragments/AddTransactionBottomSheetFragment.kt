package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.models.TransactionType

class AddTransactionBottomSheetFragment : BottomSheetDialogFragment() {

    interface AddTransactionListener {
        fun onAddIncomeClick()
        fun onAddExpenseClick()
    }

    private var listener: AddTransactionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_transaction_bottom_sheet, container, false)

        val addIncomeButton: Button = view.findViewById(R.id.add_income_button)
        val addExpenseButton: Button = view.findViewById(R.id.add_expense_button)

        addIncomeButton.setOnClickListener {
            listener?.onAddIncomeClick()
            dismiss()
        }

        addExpenseButton.setOnClickListener {
            listener?.onAddExpenseClick()
            dismiss()
        }

        return view
    }

    fun setAddTransactionListener(listener: AddTransactionListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "AddTransactionBottomSheetFragment"

        private const val ARG_TRANSACTION_TYPE = "arg_transaction_type"

        fun newInstance(transactionType: TransactionType): AddTransactionBottomSheetFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TRANSACTION_TYPE, transactionType)
            }
            return AddTransactionBottomSheetFragment().apply {
                arguments = args
            }
        }
    }
}