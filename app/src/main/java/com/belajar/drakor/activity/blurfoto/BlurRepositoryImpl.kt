package com.belajar.drakor.activity.blurfoto

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.io.File
import java.io.FileOutputStream

interface BlurRepository {
    fun applyBlur(image: Bitmap, blurLevel: Int): LiveData<Bitmap>
}
class BlurRepositoryImpl(private val application: Application) : BlurRepository {

    override fun applyBlur(image: Bitmap, blurLevel: Int): LiveData<Bitmap> {
        val result = MutableLiveData<Bitmap>()
        val inputFile = File(application.cacheDir, "input_image.png")

        FileOutputStream(inputFile).use { out ->
            image.compress(Bitmap.CompressFormat.PNG, 100, out)
        }

        val inputData = Data.Builder()
            .putString("image_path", inputFile.absolutePath)
            .putInt("blur_level", blurLevel)
            .build()

        val blurRequest = OneTimeWorkRequest.Builder(BlurWorker::class.java)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(application).enqueue(blurRequest)

        WorkManager.getInstance(application).getWorkInfoByIdLiveData(blurRequest.id)
            .observeForever { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val outputFile = File(application.cacheDir, "blurred_image.png")
                    val blurredBitmap = BitmapFactory.decodeFile(outputFile.absolutePath)
                    result.postValue(blurredBitmap)
                }
            }

        return result
    }
}