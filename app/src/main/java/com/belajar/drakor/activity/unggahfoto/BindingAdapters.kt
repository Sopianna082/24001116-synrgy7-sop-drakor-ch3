package com.belajar.drakor.activity.unggahfoto

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.belajar.drakor.R
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_profile)
        .into(view)
}