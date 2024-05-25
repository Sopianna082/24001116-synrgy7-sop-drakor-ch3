package com.belajar.drakor.activity.designpatternmvvm.model

import android.util.Log
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun registerUser(user: User) {
        userDao.insertUser(user)
        Log.d("UserRepository", "User registered: $user")
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDao.getUserByUsernameAndPassword(username, password)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun getAllUsersList(): List<User> {
        val users = userDao.getAllUsersList()
        Log.d("UserRepository", "Users in DB: $users")
        return users
    }
}
