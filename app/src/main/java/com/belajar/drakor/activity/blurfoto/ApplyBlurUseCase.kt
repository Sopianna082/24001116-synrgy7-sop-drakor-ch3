package com.belajar.drakor.activity.blurfoto

import android.graphics.Bitmap
import androidx.lifecycle.LiveData

class ApplyBlurUseCase(private val repository: BlurRepository) {

    fun execute(image: Bitmap, blurLevel: Int): LiveData<Bitmap> {
        return repository.applyBlur(image, blurLevel)
    }
}