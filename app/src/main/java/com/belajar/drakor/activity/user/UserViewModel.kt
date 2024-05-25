package com.belajar.drakor.activity.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.belajar.drakor.activity.designpatternmvvm.model.User
import com.belajar.drakor.activity.designpatternmvvm.model.UserRepository
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers

    fun insert(user: User) = viewModelScope.launch {
        repository.registerUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deleteUser(user)
    }

    fun logAllUsers() = viewModelScope.launch {
        val users = repository.getAllUsersList()
        for (user in users) {
            Log.d("UserViewModel", "User: ${user.username}, ${user.password}")
        }
    }

    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            repository.getAllUsersList()
        }
    }
}
