package com.belajar.drakor.activity.unggahfoto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.belajar.drakor.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var viewModel: UserProfileViewModel

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val inputStream = contentResolver.openInputStream(it)
            val selectedImage = BitmapFactory.decodeStream(inputStream)
            viewModel.uploadProfilePhoto(selectedImage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.buttonUploadProfile.setOnClickListener {
            getContent.launch("image/*")
        }

        viewModel.profileImage.observe(this, { bitmap ->
            binding.imageViewProfile.setImageBitmap(bitmap)
        })
    }
}