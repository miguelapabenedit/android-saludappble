package com.example.saludappble.application.modules

import com.example.saludappble.ui.login.LoginViewModel
import com.example.saludappble.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get(),get()) }
}