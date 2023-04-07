package uhk.umte.financeplusv3.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel
import uhk.umte.financeplusv3.data.FinancePlusDatabase
import uhk.umte.financeplusv3.repositories.ExpenseRepository
import uhk.umte.financeplusv3.repositories.IncomeRepository
import uhk.umte.financeplusv3.repositories.TransactionRepository
import uhk.umte.financeplusv3.repositories.impl.TransactionRepositoryImpl

val uiModule = module {
    viewModel {
        TransactionViewModel(
            get(), // Repository injection
            get(), // IncomeRepository injection
            get()  // ExpenseRepository injection
        )
    }
}

val dataModule = module {
    single { FinancePlusDatabase.getDatabase(get()) }
    single { get<FinancePlusDatabase>().transactionDao() }
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    single<IncomeRepository> { IncomeRepository(get()) }
    single<ExpenseRepository> { ExpenseRepository(get()) }
}

val repositoriesModule = module {
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
    single { IncomeRepository(get()) }
    single { ExpenseRepository(get()) }
}