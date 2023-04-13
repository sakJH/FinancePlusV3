package uhk.umte.financeplusv3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uhk.umte.financeplusv3.R
import uhk.umte.financeplusv3.models.Transaction
import androidx.fragment.app.DialogFragment

class TransactionDetailFragment : BottomSheetDialogFragment() {

    private lateinit var transaction: Transaction

    private lateinit var amountTextView: AppCompatTextView
    private lateinit var categoryTextView: AppCompatTextView
    private lateinit var dateTextView: AppCompatTextView
    private lateinit var descriptionTextView: AppCompatTextView
    private lateinit var transactionTypeTextView: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_detail, container, false)

        amountTextView = view.findViewById(R.id.transaction_amount)
        categoryTextView = view.findViewById(R.id.transaction_category)
        dateTextView = view.findViewById(R.id.transaction_date)
        descriptionTextView = view.findViewById(R.id.transaction_description)
        transactionTypeTextView = view.findViewById(R.id.transaction_type)

        setTransactionData()

        return view
    }

    fun setTransaction(transaction: Transaction) {
        this.transaction = transaction
    }

    private fun setTransactionData() {
        if (::transaction.isInitialized) {
            amountTextView.text = transaction.amount.toString()
            categoryTextView.text = transaction.category.description
            dateTextView.text = transaction.date.toString()
            descriptionTextView.text = transaction.description
            transactionTypeTextView.text = transaction.transactionType.toString()
        }
    }

    companion object {
        const val TAG = "TransactionDetailFragment"

        private const val ARG_TRANSACTION = "arg_transaction"

        fun newInstance(transaction: Transaction): TransactionDetailFragment {
            val args = Bundle().apply {
                putParcelable(ARG_TRANSACTION, transaction)
            }
            return TransactionDetailFragment().apply {
                arguments = args
            }
        }
    }
}