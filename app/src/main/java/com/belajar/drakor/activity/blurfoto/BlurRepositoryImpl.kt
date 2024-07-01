package com.belajar.drakor.activity.blurfoto

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BlurRepositoryImpl(private val context: Context) : BlurRepository {
    override suspend fun applyBlur(bitmap: Bitmap, blurLevel: Int): Bitmap {
        return withContext(Dispatchers.Default) {
            val rs = RenderScript.create(context)
            val input = Allocation.createFromBitmap(rs, bitmap)
            val output = Allocation.createTyped(rs, input.type)
            val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            script.setRadius(blurLevel.toFloat())
            script.setInput(input)
            script.forEach(output)
            output.copyTo(bitmap)
            rs.destroy()
            bitmap
        }
    }
}