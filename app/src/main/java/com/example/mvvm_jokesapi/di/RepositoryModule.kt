package com.example.mvvm_jokesapi.di

import com.example.mvvm_jokesapi.database.JokeDatabase
import com.example.mvvm_jokesapi.network.Jokes_APIServices
import com.example.mvvm_jokesapi.repository.JokesRepository
import org.koin.dsl.module

val repositoryModule= module {
    fun provideRepository(api: Jokes_APIServices, dao: JokeDatabase):JokesRepository{
        return JokesRepository(api, dao)
    }

    single { provideRepository(get(), get())
    }
}