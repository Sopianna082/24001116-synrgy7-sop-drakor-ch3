package com.belajar.drakor.activity.user

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.belajar.drakor.activity.MyApplication
import com.belajar.drakor.activity.designpatternmvvm.model.User
import com.belajar.drakor.databinding.ActivityUserBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val viewModel: UserViewModel by viewModel()
//    private val viewModel: UserViewModel by viewModels {
//        UserViewModelFactory((application as MyApplication).repository)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = UserAdapter { user -> viewModel.deleteUser(user) }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.allUsers.observe(this, Observer { users ->
            users?.let { adapter.submitList(it) }
        })

        // Log all users using lifecycleScope
        lifecycleScope.launch {
            viewModel.logAllUsers()
        }

        // Adding a test user if the database is empty
        lifecycleScope.launch {
            val users = viewModel.getUsers()
            if (users.isEmpty()) {
                viewModel.insert(User(username = "user1", password = "password1"))
                viewModel.insert(User(username = "user2", password = "password2"))
                viewModel.insert(User(username = "user3", password = "password3"))
                Log.d("UserActivity", "Inserted testuser")
            }
        }
    }
}
