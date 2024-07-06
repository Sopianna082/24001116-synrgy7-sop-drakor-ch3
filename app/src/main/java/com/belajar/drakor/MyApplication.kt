package com.belajar.drakor


import android.app.Application
import com.belajar.drakor.data.datasource.local.room.AppDatabase
import com.belajar.drakor.data.datasource.remote.ApiService
import com.belajar.drakor.data.repository.UserRepository
import com.belajar.drakor.di.appModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

class MyApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao()) }
    val apiService: ApiService by inject { parametersOf(this) }

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}