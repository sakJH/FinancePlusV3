package uhk.umte.financeplusv3.repositories

import uhk.umte.financeplusv3.data.TransactionDao
import uhk.umte.financeplusv3.models.Expense

class ExpenseRepository(private val transactionDao: TransactionDao) {

    suspend fun getAllExpenses(): List<Expense> {
        return transactionDao.getAllExpenses()
    }

    suspend fun insertExpense(expense: Expense) {
        transactionDao.insertExpense(expense)
    }

    suspend fun updateExpense(expense: Expense) {
        transactionDao.updateExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense) {
        transactionDao.deleteExpense(expense)
    }
}