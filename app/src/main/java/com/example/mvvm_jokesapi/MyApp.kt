package com.example.mvvm_jokesapi

import android.app.Application
import androidx.work.*
import com.example.mvvm_jokesapi.background.SyncDatabaseWM
import com.example.mvvm_jokesapi.di.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.concurrent.TimeUnit

class MyApp: Application() {

    private val  applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            androidLogger(Level.DEBUG)
            modules(viewModelModule, repositoryModule, netModule, apiModule, databaseModule )
        }
        runWMRequest()
    }

    private fun runWMRequest(){
        applicationScope.launch {
            setUpRecurringWorkToSyncLocalDB()
        }
    }
    private fun setUpRecurringWorkToSyncLocalDB(){
        //Declaratively define the optimal conditions for your work to run using Work Constraints
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(false)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<SyncDatabaseWM>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            SyncDatabaseWM.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )

    }
}