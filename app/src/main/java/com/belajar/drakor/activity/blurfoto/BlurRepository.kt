package com.belajar.drakor.activity.blurfoto

import android.graphics.Bitmap
import android.net.Uri

interface BlurRepository {
    suspend fun applyBlur(bitmap: Bitmap, blurLevel: Int):Bitmap
}