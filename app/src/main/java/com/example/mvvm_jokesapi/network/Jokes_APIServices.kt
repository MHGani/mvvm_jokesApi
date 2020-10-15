package com.example.mvvm_jokesapi.network

import com.example.mvvm_jokesapi.database.DatabaseJokes
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Jokes_APIServices {
    @GET(API_Calls.APP_JOKE_LIST)
    fun getJokeList(): Deferred<List<DatabaseJokes>>
}