package com.belajar.drakor.activity.designpatternmvvm.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.belajar.drakor.R
import com.belajar.drakor.activity.designpatternmvvm.model.AppDatabase
import com.belajar.drakor.activity.designpatternmvvm.model.UserRepository
import com.belajar.drakor.activity.designpatternmvvm.viewmodel.AuthViewModel
import com.belajar.drakor.activity.designpatternmvvm.viewmodel.AuthViewModelFactory
import com.belajar.drakor.activity.main.MainActivity
import com.belajar.drakor.databinding.ActivityLoginBinding
import com.belajar.drakor.datastore.UserPreferences
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    // private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        // sharedPreferences = getSharedPreferences("login_status", Context.MODE_PRIVATE)
        userPreferences = UserPreferences(this)

        // Initialize the database and UserRepository
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_database").build()
        val userRepository = UserRepository(database.userDao())

        // Initialize AuthViewModel with UserRepository
        viewModel = ViewModelProvider(this, AuthViewModelFactory(userRepository))
            .get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.loginResult.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                lifecycleScope.launch {
                    val username = viewModel.username.value ?: ""
                    userPreferences.saveLoginStatus(true, username)
                }

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

//        viewModel.loginResult.observe(this) { isLoggedIn ->
//            if (isLoggedIn) {
//                val editor = sharedPreferences.edit()
//                editor.putBoolean("is_logged_in", true)
//                editor.apply()
//
//                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
//
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            } else {
//                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
//            }
//        }

        viewModel.registerResult.observe(this) { isRegistered ->
            if (isRegistered) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }

        // Observe navigation to RegisterActivity
        viewModel.navigateToRegister.observe(this) { navigate ->
            if (navigate) {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }
}
