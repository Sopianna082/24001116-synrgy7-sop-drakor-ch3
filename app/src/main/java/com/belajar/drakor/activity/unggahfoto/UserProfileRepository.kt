package com.belajar.drakor.activity.unggahfoto

import android.graphics.Bitmap
import java.io.File

interface UserProfileRepository {
    suspend fun saveImageToInternalStorage(bitmap: Bitmap): File
    fun loadProfileImage(): Bitmap?
}