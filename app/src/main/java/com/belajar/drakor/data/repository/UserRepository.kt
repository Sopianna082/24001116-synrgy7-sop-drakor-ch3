package com.belajar.drakor.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.belajar.drakor.data.datasource.local.room.User
import com.belajar.drakor.data.datasource.local.room.UserDao

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun registerUser(user: User) {
        userDao.insertUser(user)
//        Log.d("UserRepository", "User registered: $user")
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDao.getUserByUsernameAndPassword(username, password)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun getAllUsersList(): List<User> {
        val users = userDao.getAllUsersList()
//        Log.d("UserRepository", "Users in DB: $users")
        return users
    }
}
