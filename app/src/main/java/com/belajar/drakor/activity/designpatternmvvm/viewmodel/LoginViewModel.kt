package com.belajar.drakor.activity.designpatternmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // LiveData for username and password
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    // LiveData for login  (menyimpan hasil login)
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    // LiveData for register result (menyimpan hasil register)
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult: LiveData<Boolean>
        get() = _registerResult

    // Function for login button click
    fun onLoginClick() {
        val isValid = validateCredentials(username.value, password.value)
        _loginResult.value = isValid
    }

    // Function for register button click
    fun onRegisterClick() {
        // Register logic here, for example, you can call a repository function
        // and update the register result LiveData accordingly
        _registerResult.value = true // Dummy implementation
    }

    // Function to validate username and password
    private fun validateCredentials(username: String?, password: String?): Boolean {
        // Dummy implementation, you can replace it with your own validation logic
        return !username.isNullOrBlank() && !password.isNullOrBlank()
    }
}
