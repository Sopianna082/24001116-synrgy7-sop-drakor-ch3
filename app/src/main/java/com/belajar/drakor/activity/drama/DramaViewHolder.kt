//package com.belajar.drakor.activity.drama
//
//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.belajar.drakor.R
//
//inner class DramaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
//    private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
//
//    fun bind(drama: Drama) {
//        textViewTitle.text = drama.title
//        imageViewPoster.load(drama.imageUrl) {
//            placeholder(R.drawable.placeholder)
//            error(R.drawable.error)
//        }
//
//        itemView.setOnClickListener {
//            listener.onItemClick(drama)
//        }
//    }
//}
