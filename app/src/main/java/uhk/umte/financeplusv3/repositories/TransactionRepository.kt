package uhk.umte.financeplusv3.repositories

import uhk.umte.financeplusv3.models.Expense
import uhk.umte.financeplusv3.models.Income
import uhk.umte.financeplusv3.models.Transaction
import java.util.*

interface TransactionRepository {
    suspend fun insertTransaction(transaction: Transaction): Long
    suspend fun updateTransaction(transaction: Transaction): Int
    suspend fun deleteTransaction(transaction: Transaction): Int
    suspend fun getAllTransactions(): List<Transaction>
    suspend fun getIncomeTransactions(): List<Transaction>
    suspend fun getExpenseTransactions(): List<Transaction>
    suspend fun getCurrentBalance(): Double
    suspend fun hasAnyTransactions(): Boolean

    suspend fun getTotalIncomes(): Double
    suspend fun getTotalExpenses(): Double
}