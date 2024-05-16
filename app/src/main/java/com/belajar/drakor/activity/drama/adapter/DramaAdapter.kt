package com.belajar.drakor.activity.drama.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.belajar.drakor.R
import com.belajar.drakor.activity.drama.Drama

interface OnItemClickListener {
    fun onItemClick(drama: Drama)
}

class DramaAdapter(private val dramaList: List<Drama>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<DramaAdapter.DramaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_drama, parent, false)
        return DramaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DramaViewHolder, position: Int) {
        val drama = dramaList[position]
        holder.bind(drama)
    }

    override fun getItemCount() = dramaList.size

    inner class DramaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val drama = dramaList[position]
                    listener.onItemClick(drama)
                }
            }
        }

        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)

        fun bind(drama: Drama) {
            // Set the title
            textViewTitle.text = drama.title

            // Memuat gambar dari URL menggunakan Coil
            imageViewPoster.load(drama.imageUrl) {
                placeholder(R.drawable.placeholder) // Placeholder image while loading
                error(R.drawable.error) // Image to display if loading fails
            }

            // Menangani klik pada item
            itemView.setOnClickListener {
                listener.onItemClick(drama)
            }
        }
    }
}

