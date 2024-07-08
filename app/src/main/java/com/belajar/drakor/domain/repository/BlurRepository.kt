package com.belajar.drakor.domain.repository

import android.graphics.Bitmap
import android.net.Uri

interface BlurRepository {
    suspend fun applyBlur(bitmap: Bitmap, blurLevel: Int):Bitmap
}