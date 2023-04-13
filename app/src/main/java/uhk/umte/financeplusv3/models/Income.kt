package uhk.umte.financeplusv3.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "incomes")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "category")
    val category: Category,

    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "transaction_type")
    val transactionType: TransactionType


) {
    fun toTransaction(): Transaction {
        return Transaction(id, amount, category, date, description, transactionType)
    }
}