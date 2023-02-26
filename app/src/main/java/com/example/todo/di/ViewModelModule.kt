package com.example.todo.di

import com.example.todo.features.ui.details.DetailsViewModel
import com.example.todo.features.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}