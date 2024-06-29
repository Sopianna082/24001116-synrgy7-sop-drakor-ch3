package com.belajar.drakor.activity.blurfoto

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

fun blurBitmap(context: Context, bitmap: Bitmap, blurRadius: Float): Bitmap {
    val outputBitmap = Bitmap.createBitmap(bitmap)
    val renderScript = RenderScript.create(context)
    val input = Allocation.createFromBitmap(renderScript, bitmap)
    val output = Allocation.createFromBitmap(renderScript, outputBitmap)
    val script = ScriptIntrinsicBlur.create(renderScript, input.element)
    script.setRadius(blurRadius)
    script.setInput(input)
    script.forEach(output)
    output.copyTo(outputBitmap)
    renderScript.destroy()
    return outputBitmap
}
