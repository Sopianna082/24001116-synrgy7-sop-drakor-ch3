package com.belajar.drakor.activity.blurfoto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.belajar.drakor.databinding.ActivityBlurBinding

class BlurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlurBinding
    private lateinit var viewModel: BlurViewModel
    private var selectedImage: Bitmap? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val inputStream = contentResolver.openInputStream(it)
            selectedImage = BitmapFactory.decodeStream(inputStream)
            binding.imageViewSelected.setImageBitmap(selectedImage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(BlurViewModel::class.java)

        binding.buttonPickFile.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.buttonApplyBlur.setOnClickListener {
            selectedImage?.let { image ->
                viewModel.applyBlur(image, binding.spinnerBlurLevel.selectedItemPosition)
            }
        }

        viewModel.outputBitmap.observe(this, { bitmap ->
            binding.imageViewBlurred.setImageBitmap(bitmap)
        })
    }
}