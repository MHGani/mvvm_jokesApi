package com.example.mvvm_jokesapi.di

import com.example.mvvm_jokesapi.network.Jokes_APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): Jokes_APIServices{
        return retrofit.create(Jokes_APIServices::class.java)
    }

    single { provideUserApi(get()) }
}