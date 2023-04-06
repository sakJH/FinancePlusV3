package uhk.umte.financeplusv3.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module
import uhk.umte.financeplusv3.viewmodels.TransactionViewModel

val appModules by lazy { listOf(dataModule, uiModule) }

val dataModule = module {
    //TODO
}

val uiModule = module {
    viewModels()
}


private fun Module.viewModels() {
    viewModel { TransactionViewModel(get(), get(), get()) }

    viewModelOf(::TransactionViewModel)
}

