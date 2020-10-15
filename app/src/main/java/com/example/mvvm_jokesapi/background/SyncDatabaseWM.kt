package com.example.mvvm_jokesapi.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mvvm_jokesapi.repository.JokesRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class SyncDatabaseWM (appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params), KoinComponent {

    val jokesRepository: JokesRepository by inject()

    companion object{
        const val WORK_NAME = "com.example.mvvm_jokesapi.background.SyncDatabaseWM"
    }
    override suspend fun doWork(): Result {
       // refreshJokes
        try {
            jokesRepository.refreshJokes()
        }catch (e:Exception){
            return Result.retry()
        }
        return Result.success()
    }
}