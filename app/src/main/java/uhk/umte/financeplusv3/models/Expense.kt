package uhk.umte.financeplusv3.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
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