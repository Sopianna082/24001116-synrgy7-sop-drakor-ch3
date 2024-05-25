package com.belajar.drakor.activity.designpatternmvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.belajar.drakor.R
import com.belajar.drakor.activity.designpatternmvvm.model.AppDatabase
import com.belajar.drakor.activity.designpatternmvvm.model.UserRepository
import com.belajar.drakor.activity.designpatternmvvm.viewmodel.AuthViewModel
import com.belajar.drakor.activity.designpatternmvvm.viewmodel.AuthViewModelFactory
import com.belajar.drakor.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        // Initialize the database and UserRepository
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_database").build()
        val userRepository = UserRepository(database.userDao())

        // Initialize AuthViewModel with UserRepository
        viewModel = ViewModelProvider(this, AuthViewModelFactory(userRepository))
            .get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.registerResult.observe(this) { isRegistered ->
            if (isRegistered) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.navigateToLogin.observe(this) { navigate ->
            if (navigate) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}