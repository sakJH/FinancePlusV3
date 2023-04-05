package uhk.umte.financeplusv3.repositories

import uhk.umte.financeplusv3.data.TransactionDao
import uhk.umte.financeplusv3.models.Income

class IncomeRepository(private val transactionDao: TransactionDao) {

    suspend fun getAllIncomes(): List<Income> {
        return transactionDao.getAllIncomes()
    }

    suspend fun insertIncome(income: Income) {
        transactionDao.insertIncome(income)
    }

    suspend fun updateIncome(income: Income) {
        transactionDao.updateIncome(income)
    }

    suspend fun deleteIncome(income: Income) {
        transactionDao.deleteIncome(income)
    }
}