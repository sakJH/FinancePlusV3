package uhk.umte.financeplusv3.repositories.impl

import androidx.annotation.WorkerThread
import uhk.umte.financeplusv3.data.TransactionDao
import uhk.umte.financeplusv3.models.Expense
import uhk.umte.financeplusv3.models.Income
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.repositories.TransactionRepository
import java.util.*

class TransactionRepositoryImpl(private val transactionDao: TransactionDao) : TransactionRepository {

    @WorkerThread
    override suspend fun insertTransaction(transaction: Transaction): Long {
        return transactionDao.insertTransaction(transaction)
    }

    @WorkerThread
    override suspend fun updateTransaction(transaction: Transaction): Int {
        return transactionDao.updateTransaction(transaction)
    }

    @WorkerThread
    override suspend fun deleteTransaction(transaction: Transaction): Int {
        return transactionDao.deleteTransaction(transaction)
    }

    @WorkerThread
    override suspend fun getAllTransactions(): List<Transaction> {
        return transactionDao.getAllTransactions()
    }

    @WorkerThread
    override suspend fun getIncomeTransactions(): List<Transaction> {
        return transactionDao.getTransactionsByType("income")
    }

    @WorkerThread
    override suspend fun getExpenseTransactions(): List<Transaction> {
        return transactionDao.getTransactionsByType("expense")
    }

    @WorkerThread
    override suspend fun getCurrentBalance(): Double {
        val totalIncome = transactionDao.getTotalIncome() ?: 0.0
        val totalExpense = transactionDao.getTotalExpense() ?: 0.0
        return totalIncome - totalExpense
    }

    override suspend fun hasAnyTransactions(): Boolean {
        return transactionDao.countTransactions() > 0
    }

    override suspend fun getTotalIncomes(): Double {
        return transactionDao.getAllIncomes().sumByDouble { it.amount }
    }

    override suspend fun getTotalExpenses(): Double {
        return transactionDao.getAllExpenses().sumByDouble { it.amount }
    }

}