package com.belajar.drakor.activity.home

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.belajar.drakor.R

class ImageLoaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_loader)

        val imageView: ImageView = findViewById(R.id.imageView)

        // URL gambar dari internet
        val imageUrl = "https://i.ibb.co/zJHYGBP/binarlogo.jpg"

        // Memuat gambar menggunakan Glide
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}