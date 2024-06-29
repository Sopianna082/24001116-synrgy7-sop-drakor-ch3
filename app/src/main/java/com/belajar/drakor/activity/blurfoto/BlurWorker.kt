package com.belajar.drakor.activity.blurfoto

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream

class BlurWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val imagePath = inputData.getString("image_path") ?: return Result.failure()
        val blurLevel = inputData.getInt("blur_level", 1)

        return try {
            val bitmap = BitmapFactory.decodeFile(imagePath) ?: return Result.failure()
            val blurredBitmap = blurBitmap(applicationContext, bitmap, blurLevel * 5f)
            val outputFile = File(applicationContext.cacheDir, "blurred_image.png")

            FileOutputStream(outputFile).use { out ->
                blurredBitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
