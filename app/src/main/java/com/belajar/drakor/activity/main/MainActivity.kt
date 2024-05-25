//package com.belajar.drakor.activity.main
//
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import androidx.fragment.app.FragmentTransaction
//import com.belajar.drakor.R
//import com.belajar.drakor.activity.home.HomeActivity
//import com.belajar.drakor.activity.designpatternmvvm.view.LoginActivity
//import com.belajar.drakor.activity.user.UserFragment
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var sharedPreferences: SharedPreferences
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        sharedPreferences = getSharedPreferences("login_status", Context.MODE_PRIVATE)
//
//        // Check if user is logged in
//        val isLoggedIn = checkIfLoggedIn()
//        if (isLoggedIn) {
//            setContentView(R.layout.activity_main)
//        } else {
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
//
//        val buttonDrakor: Button = findViewById(R.id.buttonDrakor)
//        buttonDrakor.setOnClickListener{
//            startActivity(Intent(this, HomeActivity::class.java))
//        }
//
//        val buttonLogout: Button = findViewById(R.id.buttonLogout)
//        buttonLogout.setOnClickListener {
//            logout()
//        }
//
//        val buttonUser: Button = findViewById(R.id.buttonUser)
//        buttonUser.setOnClickListener {
//            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, UserFragment())
//            transaction.commit()
//            Log.d("MainActivity", "Button user clicked")
//        }
//    }
//
//    private fun checkIfLoggedIn(): Boolean {
//        return sharedPreferences.getBoolean("is_logged_in", false)
//    }
//
//    private fun logout() {
//        val editor = sharedPreferences.edit()
//        editor.remove("is_logged_in")
//        editor.apply()
//
//        val intent = Intent(this, LoginActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)
//        finish()
//    }
//}

package com.belajar.drakor.activity.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.belajar.drakor.R
import com.belajar.drakor.activity.designpatternmvvm.view.LoginActivity
import com.belajar.drakor.activity.home.HomeActivity
import com.belajar.drakor.activity.user.UserActivity
import com.belajar.drakor.activity.user.UserFragment

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    private fun checkIfLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("is_logged_in", false)
    }

    private fun logout() {
        val editor = sharedPreferences.edit()
        editor.remove("is_logged_in")
        editor.apply()

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("login_status", Context.MODE_PRIVATE)

        val isLoggedIn = checkIfLoggedIn()
        if (isLoggedIn) {
            setContentView(R.layout.activity_main)

            val buttonDrakor: Button = findViewById(R.id.buttonDrakor)
            buttonDrakor.setOnClickListener {
                startActivity(Intent(this, HomeActivity::class.java))
            }

            val buttonLogout: Button = findViewById(R.id.buttonLogout)
            buttonLogout.setOnClickListener {
                logout()
            }

            val buttonUser: Button = findViewById(R.id.buttonUser)
            buttonUser.setOnClickListener {
                try {
                    Log.d("MainActivity", "Button user clicked")
//                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//                    transaction.replace(R.id.fragment_container, UserFragment())
//                    transaction.addToBackStack(null)
//                    transaction.commit()
                    startActivity(Intent(this, UserActivity::class.java))
                    finish()
                    Log.d("MainActivity", "UserActivity replaced")
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error replacing fragment", e)
                }
            }

        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

