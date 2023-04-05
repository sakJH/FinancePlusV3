package uhk.umte.financeplusv3.repositories.impl

import androidx.annotation.WorkerThread
import uhk.umte.financeplusv3.data.TransactionDao
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.repositories.TransactionRepository

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
}