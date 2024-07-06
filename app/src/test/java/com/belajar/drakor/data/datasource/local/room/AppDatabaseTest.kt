package com.belajar.drakor.data.datasource.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.belajar.drakor.data.datasource.local.room.AppDatabase
import com.belajar.drakor.data.datasource.local.room.User
import com.belajar.drakor.data.datasource.local.room.UserDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest= Config.NONE)
class AppDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = db.userDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetUser() = runBlocking {
        val user = User(username = "test_user", password = "test_password")
        userDao.insertUser(user)
        val retrievedUser = userDao.getUserByUsernameAndPassword("test_user", "test_password")
        assert(retrievedUser != null)
        assert(retrievedUser!!.username == "test_user")
    }

    @Test
    fun getAllUsers() = runBlocking {
        val user1 = User(username = "user1", password = "password1")
        val user2 = User(username = "user2", password = "password2")
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        val userList = userDao.getAllUsersList()
        assert(userList.size == 2)
    }

    @Test
    fun deleteUser() = runBlocking {
        val user = User(username = "test_user", password = "test_password")
        userDao.insertUser(user)
        userDao.deleteUser(user)
        val deletedUser = userDao.getUserByUsernameAndPassword("test_user", "test_password")
    }
}
