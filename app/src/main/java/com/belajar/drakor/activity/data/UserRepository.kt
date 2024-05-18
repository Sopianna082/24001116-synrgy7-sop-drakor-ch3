package com.belajar.drakor.activity.data

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDao.getUserByUsernameAndPassword(username, password)
    }
}
