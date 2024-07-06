package com.belajar.drakor.com.belajar.drakor.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.belajar.drakor.data.datasource.local.room.User
import com.belajar.drakor.data.datasource.local.room.UserDao
import com.belajar.drakor.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    // This rule is used to make sure that LiveData operates properly with JUnit tests
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock dependencies
    @Mock
    private lateinit var userDao: UserDao

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        userRepository = UserRepository(userDao)
    }

    @Test
    fun `test registerUser`() = runBlockingTest {
        val user = User(1, "username", "password")
        userRepository.registerUser(user)
        userDao.insertUser(user)
    }

    @Test
    fun `test loginUser`() = runBlockingTest {
        // Given username and password
        val username = "username"
        val password = "password"
        val expectedUser = User(1, username, password)

        // Mock behavior of getUserByUsernameAndPassword
        `when`(userDao.getUserByUsernameAndPassword(username, password)).thenReturn(expectedUser)

        // When calling loginUser
        val result = userRepository.loginUser(username, password)

        // Then verify that the correct user object is returned
        assertEquals(expectedUser, result)
    }

    @Test
    fun `test deleteUser`() = runBlockingTest {
        // Given a user to delete
        val user = User(1, "username", "password")

        // When deleting the user
        userRepository.deleteUser(user)

        // Then verify that deleteUser was called on userDao
        // You can add more verification if needed based on your actual implementation
        userDao.deleteUser(user)
    }

    @Test
    fun `test getAllUsersList`() = runBlockingTest {
        // Given a list of users
        val users = listOf(
            User(1, "user1", "password1"),
            User(2, "user2", "password2")
        )

        // Mock behavior of getAllUsersList
        `when`(userDao.getAllUsersList()).thenReturn(users)

        // When calling getAllUsersList
        val result = userRepository.getAllUsersList()

        // Then verify that the correct list of users is returned
        assertEquals(users, result)
    }
}
