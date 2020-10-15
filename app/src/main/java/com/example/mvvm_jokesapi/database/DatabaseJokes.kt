package com.example.mvvm_jokesapi.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DatabaseJokes(
    @PrimaryKey
    var id : Int,
    var joke : String,
    var categories : List<String>) : Parcelable{}
