package com.example.mvvm_jokesapi.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.example.mvvm_jokesapi.database.DatabaseJokes

class JokesDetailViewModel: ViewModel(){

    // The internal MutableLiveData for the selected character
    private val _selectedProperty = MutableLiveData<DatabaseJokes>()

    // The external LiveData for the SelectedCharacter
    val selectedProperty: LiveData<DatabaseJokes>
        get() = _selectedProperty


    fun setProperty(jokeProperty: DatabaseJokes){
        _selectedProperty.value = jokeProperty
    }

}