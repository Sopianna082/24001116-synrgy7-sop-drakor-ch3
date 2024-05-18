package com.belajar.drakor.activity.designpatternmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajar.drakor.activity.designpatternmvvm.model.User
import com.belajar.drakor.activity.designpatternmvvm.model.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean> = _registerResult

    private val _navigateToRegister = MutableLiveData<Boolean>()
    val navigateToRegister: LiveData<Boolean> = _navigateToRegister

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> = _navigateToLogin

    fun onLoginClick() {
        viewModelScope.launch {
            val user = userRepository.loginUser(username.value.orEmpty(), password.value.orEmpty())
            _loginResult.value = user != null
        }
    }

    fun onRegisterClick() {
        _navigateToRegister.value = true
    }

    fun registerUser() {
        viewModelScope.launch {
            val user = User(username = username.value.orEmpty(), password = password.value.orEmpty())
            userRepository.registerUser(user)
            _registerResult.value = true // Update this according to your registration logic
            _navigateToLogin.value = true // Navigate to login after registration
        }
    }
}
