package com.example.mvvm_jokesapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_jokesapi.database.DatabaseJokes
import com.example.mvvm_jokesapi.repository.JokesRepository
import kotlinx.coroutines.*
import java.lang.Exception

class JokeListViewModel (private val jokeRepository: JokesRepository): ViewModel(){
    private val viewModelJob = SupervisorJob()
    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)


    val jokeListResults = jokeRepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository(){
        viewModelScope.launch {
            try {
                jokeRepository.refreshJokes()
            }
            catch(networkError: Exception){

            }
        }
    }

    private val _navigateToSelectedProperty = MutableLiveData<DatabaseJokes>()
    val navigateToSelectedProperty: LiveData<DatabaseJokes>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(jokeProperty: DatabaseJokes) {
        _navigateToSelectedProperty.value = jokeProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}