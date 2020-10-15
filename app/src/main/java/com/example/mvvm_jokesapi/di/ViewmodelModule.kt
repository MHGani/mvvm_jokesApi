package com.example.mvvm_jokesapi.di

import com.example.mvvm_jokesapi.viewmodel.JokeListViewModel
import com.example.mvvm_jokesapi.viewmodel.JokesDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { JokeListViewModel(get()) }
    viewModel { JokesDetailViewModel() }
}