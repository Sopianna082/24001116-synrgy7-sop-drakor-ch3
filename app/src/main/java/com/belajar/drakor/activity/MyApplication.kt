package com.belajar.drakor.activity


import android.app.Application
import com.belajar.drakor.activity.designpatternmvvm.model.AppDatabase
import com.belajar.drakor.activity.designpatternmvvm.model.UserRepository

class MyApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UserRepository(database.userDao()) }
}