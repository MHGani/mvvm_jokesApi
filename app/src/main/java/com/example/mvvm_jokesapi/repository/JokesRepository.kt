package com.example.mvvm_jokesapi.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import com.example.mvvm_jokesapi.database.DatabaseJokes
import com.example.mvvm_jokesapi.database.JokeDatabase
import com.example.mvvm_jokesapi.network.Jokes_APIServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokesRepository(private val jokesApiservices: Jokes_APIServices, private val database: JokeDatabase)
{
    suspend fun refreshJokes(){
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO){
            val jokesList= jokesApiservices.getJokeList().await()
            database.jokeDoa.insertAll(jokesList)
        }
    }

    val results: LiveData<List<DatabaseJokes>> = database.jokeDoa.getLocalDBJokes()
}