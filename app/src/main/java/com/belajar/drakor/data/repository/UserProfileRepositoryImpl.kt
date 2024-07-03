package com.belajar.drakor.data.repository

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.belajar.drakor.domain.repository.UserProfileRepository
import java.io.File
import java.io.FileOutputStream

class UserProfileRepositoryImpl(private val application: Application) : UserProfileRepository {

    override suspend fun saveImageToInternalStorage(bitmap: Bitmap): File {
        val file = File(application.filesDir, "profile_image.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return file
    }

    override fun loadProfileImage(): Bitmap? {
        val file = File(application.filesDir, "profile_image.png")
        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }
}