package com.belajar.drakor.activity.blurfoto


import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.io.File
import java.io.FileOutputStream

class BlurViewModel(application: Application) : AndroidViewModel(application) {

    private val _outputBitmap = MutableLiveData<Bitmap>()
    val outputBitmap: LiveData<Bitmap> get() = _outputBitmap

    fun applyBlur(bitmap: Bitmap, blurLevel: Int) {
        val inputFile = File(getApplication<Application>().cacheDir, "input_image.png")

        FileOutputStream(inputFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }

        val inputData = Data.Builder()
            .putString("image_path", inputFile.absolutePath)
            .putInt("blur_level", blurLevel)
            .build()

        val blurRequest = OneTimeWorkRequest.Builder(BlurWorker::class.java)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(getApplication()).enqueue(blurRequest)

        WorkManager.getInstance(getApplication()).getWorkInfoByIdLiveData(blurRequest.id)
            .observeForever { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val outputFile = File(getApplication<Application>().cacheDir, "blurred_image.png")
                    val blurredBitmap = BitmapFactory.decodeFile(outputFile.absolutePath)
                    _outputBitmap.postValue(blurredBitmap)
                }
            }
    }
}