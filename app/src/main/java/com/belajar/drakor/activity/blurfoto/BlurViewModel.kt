package com.belajar.drakor.activity.blurfoto

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class BlurViewModel(application: Application, private val repository: BlurRepository) : AndroidViewModel(application) {

    private val _outputBitmap = MutableLiveData<Bitmap>()
    val outputBitmap: LiveData<Bitmap> get() = _outputBitmap

    private val _workInfo = MutableLiveData<WorkInfo>()
    val workInfo: LiveData<WorkInfo> get() = _workInfo

    private lateinit var blurredImageFile: File

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
                _workInfo.postValue(workInfo)
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    blurredImageFile = File(getApplication<Application>().cacheDir, "blurred_image.png")
                    val blurredBitmap = BitmapFactory.decodeFile(blurredImageFile.absolutePath)
                    _outputBitmap.postValue(blurredBitmap)
                }
            }
    }

    fun getBlurredImageUri(): Uri {
        return FileProvider.getUriForFile(
            getApplication(),
            "${getApplication<Application>().packageName}.provider",
            blurredImageFile
        )
    }
}