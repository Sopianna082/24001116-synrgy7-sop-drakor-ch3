package com.belajar.drakor.activity.auth.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.belajar.drakor.data.datasource.local.room.User
import com.belajar.drakor.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class AuthViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var viewModel: AuthViewModel

    @Mock
    private lateinit var loginObserver: Observer<Boolean>

    @Mock
    private lateinit var registerObserver: Observer<Boolean>

    @Mock
    private lateinit var navigateToLoginObserver: Observer<Boolean>

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        viewModel = AuthViewModel(userRepository)
        registerObserver = mock(Observer::class.java) as Observer<Boolean>
    }

    @Test
    fun `given invalid credentials when login then return false`() = runBlockingTest {
        // Given
        val username = "test"
        val password = "wrong_password"
        `when`(userRepository.loginUser(username, password)).thenReturn(null)

        viewModel.username.value = username
        viewModel.password.value = password

        // When
        viewModel.loginResult.observeForever(loginObserver)
        viewModel.onLoginClick()

        // Then
        verify(loginObserver).onChanged(false)
    }

    @Test
    fun `when register user then navigate to login`() = runBlockingTest {
        // Given
        val username = "new_user"
        val password = "new_password"
        val user = User(username = username, password = password)
        `when`(userRepository.registerUser(user)).thenReturn(Unit)

        viewModel.username.value = username
        viewModel.password.value = password

        // When
        viewModel.registerResult.observeForever(registerObserver)
        viewModel.navigateToLogin.observeForever(navigateToLoginObserver)
        viewModel.registerUser()

        // Then
        verify(userRepository).registerUser(user)
        verify(registerObserver).onChanged(true)
        verify(navigateToLoginObserver).onChanged(true)
    }
}