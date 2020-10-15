package com.example.mvvm_jokesapi.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm_jokesapi.util.Converters

@Dao
interface JokesDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jokes : List<DatabaseJokes>)

    @Query("SELECT * FROM DatabaseJokes")
    fun getLocalDBJokes(): LiveData<List<DatabaseJokes>>
}

@Database(entities = [DatabaseJokes::class], version  = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JokeDatabase: RoomDatabase(){
    abstract val jokeDoa: JokesDoa
}