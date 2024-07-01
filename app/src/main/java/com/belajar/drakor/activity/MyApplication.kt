package com.belajar.drakor.activity


import android.app.Application
import com.belajar.drakor.activity.designpatternmvvm.model.AppDatabase
import com.belajar.drakor.activity.designpatternmvvm.model.UserRepository
import com.belajar.drakor.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao()) }

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}