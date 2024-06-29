package com.belajar.drakor.activity.unggahfoto

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val _profileImage = MutableLiveData<Bitmap>()
    val profileImage: LiveData<Bitmap> get() = _profileImage

    private val _profileImageUrl = MutableLiveData<String>()
    val profileImageUrl: LiveData<String> get() = _profileImageUrl

    init {
        loadProfileImage()
    }

    fun uploadProfilePhoto(bitmap: Bitmap) {
        viewModelScope.launch {
            val profileImageFile = saveImageToInternalStorage(bitmap)
            _profileImage.postValue(bitmap)
            _profileImageUrl.postValue(profileImageFile.absolutePath)
        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): File {
        val file = File(getApplication<Application>().filesDir, "profile_image.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return file
    }

    private fun loadProfileImage() {
        viewModelScope.launch {
            val file = File(getApplication<Application>().filesDir, "profile_image.png")
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                _profileImage.postValue(bitmap)
                _profileImageUrl.postValue(file.absolutePath)
            }
        }
    }
}