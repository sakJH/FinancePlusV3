package uhk.umte.financeplusv3.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import uhk.umte.financeplusv3.models.Expense
import uhk.umte.financeplusv3.models.Income
import uhk.umte.financeplusv3.models.Transaction

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction): Long

    @Update
    suspend fun updateTransaction(transaction: Transaction): Int

    @Delete
    suspend fun deleteTransaction(transaction: Transaction): Int

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE transactionType = :transactionType")
    suspend fun getTransactionsByType(transactionType: String): List<Transaction>

    @Insert
    suspend fun insertIncome(income: Income): Long

    @Update
    suspend fun updateIncome(income: Income): Int

    @Delete
    suspend fun deleteIncome(income: Income): Int

    @Query("SELECT * FROM incomes")
    suspend fun getAllIncomes(): List<Income>

    @Insert
    suspend fun insertExpense(expense: Expense): Long

    @Update
    suspend fun updateExpense(expense: Expense): Int

    @Delete
    suspend fun deleteExpense(expense: Expense): Int

    @Query("SELECT * FROM expenses")
    suspend fun getAllExpenses(): List<Expense>
}