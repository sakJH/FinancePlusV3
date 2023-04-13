package uhk.umte.financeplusv3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import uhk.umte.financeplusv3.models.Expense
import uhk.umte.financeplusv3.models.Income
import uhk.umte.financeplusv3.models.Transaction
import uhk.umte.financeplusv3.repositories.ExpenseRepository
import uhk.umte.financeplusv3.repositories.IncomeRepository
import uhk.umte.financeplusv3.repositories.TransactionRepository

class TransactionViewModel(
    private val repository: TransactionRepository,
    private val incomeRepository: IncomeRepository,
    private val expenseRepository: ExpenseRepository
) : ViewModel(), KoinComponent {

    private val _allTransactions = MutableLiveData<List<Transaction>>()
    val allTransactions: LiveData<List<Transaction>> = _allTransactions

    private val _incomeTransactions = MutableLiveData<List<Transaction>>()
    val incomeTransactions: LiveData<List<Transaction>> = _incomeTransactions

    private val _expenseTransactions = MutableLiveData<List<Transaction>>()
    val expenseTransactions: LiveData<List<Transaction>> = _expenseTransactions

    private val _allIncomes = MutableLiveData<List<Income>>()
    val allIncomes: LiveData<List<Income>> = _allIncomes

    private val _allExpenses = MutableLiveData<List<Expense>>()
    val allExpenses: LiveData<List<Expense>> = _allExpenses

    private val _currentBalance = MutableLiveData<Double>()
    val currentBalance: LiveData<Double> = _currentBalance

    //Získání celkových příjmů pro main fragment
    private val _totalIncomes = MutableLiveData<Double>()
    val totalIncomes: LiveData<Double> get() = _totalIncomes

    //Získání celkových výdajů pro main fragment
    private val _totalExpenses = MutableLiveData<Double>()
    val totalExpenses: LiveData<Double> get() = _totalExpenses

    init {
        loadAllTransactions()
        loadIncomeTransactions()
        loadExpenseTransactions()
        loadCurrentBalance()
        loadTotalIncomes()
        loadTotalExpenses()

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

    private fun loadCurrentBalance() = viewModelScope.launch {
        _currentBalance.value = repository.getCurrentBalance()
    }

    suspend fun hasAnyTransactions(): Boolean {
        return repository.hasAnyTransactions()
    }

    fun loadTotalIncomes() {
        viewModelScope.launch {
            _totalIncomes.value = repository.getTotalIncomes()
        }
    }

    fun loadTotalExpenses() {
        viewModelScope.launch {
            _totalExpenses.value = repository.getTotalExpenses()
        }
    }
}