package com.example.mvvm_jokesapi.di

import android.app.Application
import androidx.room.Room
import com.example.mvvm_jokesapi.database.JokeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

//Module: which return me the instance of the class
val databaseModule = module {
    fun providersDatabase(application: Application):JokeDatabase{
        return Room.databaseBuilder(application, JokeDatabase::class.java, "jokes.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    //singleton: single instance
    single { providersDatabase(androidApplication()) // lifecycle of the application
    }
}