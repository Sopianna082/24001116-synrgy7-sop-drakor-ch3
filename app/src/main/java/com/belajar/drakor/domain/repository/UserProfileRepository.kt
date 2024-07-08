package com.belajar.drakor.domain.repository

import android.graphics.Bitmap
import java.io.File

interface UserProfileRepository {
    suspend fun saveImageToInternalStorage(bitmap: Bitmap): File
    fun loadProfileImage(): Bitmap?
}