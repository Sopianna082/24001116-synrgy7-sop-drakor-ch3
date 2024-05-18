package com.belajar.drakor.activity.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.belajar.drakor.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inisialisasi NavHostFragment
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//
//        // Inisialisasi BottomNavigationView
//        bottomNavigationView = findViewById(R.id.bottomNavigationView)
//
//        // Hubungkan BottomNavigationView dengan NavController
//        bottomNavigationView.setupWithNavController(navController)
//
//        // OnClick pada buttonDrakor
//        val buttonDrakor: Button = findViewById(R.id.buttonDrakor)
//        buttonDrakor.setOnClickListener{
//            startActivity(Intent(this, NavActivity::class.java))
//        }

        // Cek apakah fragment ditemukan dan valid
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (navHostFragment is NavHostFragment) {
            navController = navHostFragment.navController
        } else {
            // Handle jika fragment tidak ditemukan atau tidak valid
            Log.e("MainActivity", "NavHostFragment not found or invalid")
        }

        // Inisialisasi BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Hubungkan BottomNavigationView dengan NavController
        if (::navController.isInitialized) {
            bottomNavigationView.setupWithNavController(navController)
        } else {
            // Handle jika navController belum diinisialisasi
            Log.e("MainActivity", "NavController is not initialized")
        }

    }
}