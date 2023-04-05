package uhk.umte.financeplusv3.models

import java.util.*

data class Expense(
    val id: Int,
    val amount: Double,
    val category: String,
    val date: Date,
    val description: String
) {
    fun toTransaction(): Transaction {
        return Transaction(id, amount, category, date, description, "expense")
    }
}