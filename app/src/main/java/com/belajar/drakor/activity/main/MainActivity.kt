package com.belajar.drakor.activity.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.belajar.drakor.R
import com.belajar.drakor.activity.home.HomeActivity
import com.belajar.drakor.activity.home.NavActivity
import com.belajar.drakor.activity.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private fun checkIfLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("is_logged_in", false)
    }

    private fun logout() {
        // Remove login status from SharedPreferences
        val editor = sharedPreferences.edit()
        editor.remove("is_logged_in")
        editor.apply()

        // Redirect to LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("login_status", Context.MODE_PRIVATE)

        // Check if user is logged in
        val isLoggedIn = checkIfLoggedIn()
        if (isLoggedIn) {
            // If user is already logged in
//            startActivity(Intent(this, HomeActivity::class.java))
//            finish()
            setContentView(R.layout.activity_main)
        } else {
            // If user is not logged in
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // OnClick pada buttonDrakor
        val buttonDrakor: Button = findViewById(R.id.buttonDrakor)
        buttonDrakor.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        val buttonLogout: Button = findViewById(R.id.buttonLogout)
        buttonLogout.setOnClickListener {
            logout()
        }
    }
}
