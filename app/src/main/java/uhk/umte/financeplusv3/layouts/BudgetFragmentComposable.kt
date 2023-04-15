package uhk.umte.financeplusv3.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uhk.umte.financeplusv3.models.Category
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.models.TransactionType
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import java.util.*

@Composable
fun BudgetFragmentComposable(
    viewModel: TransactionViewModel,
    onTransactionItemClick: (Transaction) -> Unit
) {
   /* val transactions = viewModel.allTransactions.collectAsState(emptyList())

    LazyColumn {
        items(transactions.value) { transaction ->
            TransactionItem(
                transaction = transaction,
                onTransactionItemClick = onTransactionItemClick
            )
        }
    }*/
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    onTransactionItemClick: (Transaction) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onTransactionItemClick(transaction) }
    ) {
        transaction.description?.let { Text(text = it) }
        Text(text = "${transaction.amount}")
    }
}

@Preview
@Composable
fun TransactionItemPreview() {
    val transaction = Transaction(
        id = 0,
        amount = 3.0,
        category = Category.OTHER,
        date = Date(System.currentTimeMillis()),
        description = "První příjem",
        transactionType = TransactionType.INCOME
    )
    TransactionItem(transaction = transaction, onTransactionItemClick = {})
}