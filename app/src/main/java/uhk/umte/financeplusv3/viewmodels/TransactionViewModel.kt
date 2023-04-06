package uhk.umte.financeplusv3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uhk.umte.financeplusv3.models.Expense
import uhk.umte.financeplusv3.models.Income
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.repositories.ExpenseRepository
import uhk.umte.financeplusv3.repositories.IncomeRepository
import uhk.umte.financeplusv3.repositories.TransactionRepository

class TransactionViewModel(
    private val repository: TransactionRepository, private val incomeRepository: IncomeRepository,
                           private val expenseRepository: ExpenseRepository
) : ViewModel() {

    private val _allTransactions = MutableLiveData<List<Transaction>>()
    val allTransactions = _allTransactions

    private val _incomeTransactions = MutableLiveData<List<Transaction>>()
    val incomeTransactions = _incomeTransactions

    private val _expenseTransactions = MutableLiveData<List<Transaction>>()
    val expenseTransactions = _expenseTransactions

    private val _allIncomes = MutableLiveData<List<Income>>()
    val allIncomes: LiveData<List<Income>> = _allIncomes

    private val _allExpenses = MutableLiveData<List<Expense>>()
    val allExpenses: LiveData<List<Expense>> = _allExpenses

    init {
        loadAllTransactions()
        loadIncomeTransactions()
        loadExpenseTransactions()
    }

    private fun loadAllTransactions() = viewModelScope.launch {
        _allTransactions.value = repository.getAllTransactions()
    }

    private fun loadIncomeTransactions() = viewModelScope.launch {
        _incomeTransactions.value = repository.getIncomeTransactions()
    }

    private fun loadExpenseTransactions() = viewModelScope.launch {
        _expenseTransactions.value = repository.getExpenseTransactions()
    }

    // Metoda pro přidání transakce
    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insertTransaction(transaction)
        loadAllTransactions()
        loadIncomeTransactions()
        loadExpenseTransactions()
    }

    // Metoda pro aktualizaci transakce
    fun update(transaction: Transaction) = viewModelScope.launch {
        repository.updateTransaction(transaction)
        loadAllTransactions()
        loadIncomeTransactions()
        loadExpenseTransactions()
    }

    // Metoda pro smazání transakce
    fun delete(transaction: Transaction) = viewModelScope.launch {
        repository.deleteTransaction(transaction)
        loadAllTransactions()
        loadIncomeTransactions()
        loadExpenseTransactions()
    }

    // Metody pro Income

    init {
        getAllIncomes()
        getAllExpenses()
    }


    private fun getAllIncomes() = viewModelScope.launch {
        _allIncomes.value = incomeRepository.getAllIncomes()
    }

    fun insertIncome(income: Income) = viewModelScope.launch {
        incomeRepository.insertIncome(income)
        getAllIncomes()
    }

    fun updateIncome(income: Income) = viewModelScope.launch {
        incomeRepository.updateIncome(income)
        getAllIncomes()
    }

    fun deleteIncome(income: Income) = viewModelScope.launch {
        incomeRepository.deleteIncome(income)
        getAllIncomes()
    }

    // Metody pro Expense

    private fun getAllExpenses() = viewModelScope.launch {
        _allExpenses.value = expenseRepository.getAllExpenses()
    }

    fun insertExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.insertExpense(expense)
        getAllExpenses()
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.updateExpense(expense)
        getAllExpenses()
    }

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        expenseRepository.deleteExpense(expense)
        getAllExpenses()
    }
}