package com.belajar.drakor.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.belajar.drakor.retrofit.PeopleListActivity
import com.belajar.drakor.R
import com.belajar.drakor.activity.blurfoto.BlurActivity
import com.belajar.drakor.activity.designpatternmvvm.view.LoginActivity
import com.belajar.drakor.activity.home.HomeActivity
import com.belajar.drakor.activity.home.ImageLoaderActivity
import com.belajar.drakor.activity.home.LocationActivity
import com.belajar.drakor.activity.user.UserActivity
import com.belajar.drakor.datastore.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userPreferences: UserPreferences

    private fun checkIfLoggedIn(): Boolean {
        var isLoggedIn = false
        lifecycleScope.launch {
            isLoggedIn = userPreferences.isLoggedIn.first()
        }
        return isLoggedIn
    }

    private fun logout() {

        lifecycleScope.launch {
            userPreferences.clearLoginStatus()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferences = UserPreferences(this)

        lifecycleScope.launch {
            val isLoggedIn = userPreferences.isLoggedIn.first()
            if (isLoggedIn) {
                setContentView(R.layout.activity_main)

                // Show logged-in user's name
                val username = userPreferences.username.first()
                val greetingTextView: TextView = findViewById(R.id.greetingTextView)
                greetingTextView.text = "Haloo $username"

                val buttonDrakor: Button = findViewById(R.id.buttonDrakor)
                buttonDrakor.setOnClickListener {
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                }

                val buttonLogout: Button = findViewById(R.id.buttonLogout)
                buttonLogout.setOnClickListener {
                    logout()
                }

                val buttonLoadImage: Button = findViewById(R.id.buttonLoadImage)
                buttonLoadImage.setOnClickListener {
                    startActivity(Intent(this@MainActivity, ImageLoaderActivity::class.java))
                }

                val buttonLoadLocation: Button = findViewById(R.id.buttonLoadLocation)
                buttonLoadLocation.setOnClickListener{
                    startActivity(Intent(this@MainActivity, LocationActivity::class.java))
                }

                val buttonPerson: Button = findViewById(R.id.buttonPerson)
                buttonPerson.setOnClickListener{
                    startActivity(Intent(this@MainActivity, PeopleListActivity::class.java))
                }

                val buttonUser: Button = findViewById(R.id.buttonUser)
                buttonUser.setOnClickListener {
                    try {
                        Log.d("MainActivity", "Button user clicked")
                        startActivity(Intent(this@MainActivity, UserActivity::class.java))
                        Log.d("MainActivity", "UserActivity replaced")
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error replacing fragment", e)
                    }
                }

                val buttonBlurFoto: Button = findViewById(R.id.buttonBlurFoto)
                buttonBlurFoto.setOnClickListener {
                    startActivity(Intent(this@MainActivity, BlurActivity::class.java))
                }
            } else {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
    }
}

