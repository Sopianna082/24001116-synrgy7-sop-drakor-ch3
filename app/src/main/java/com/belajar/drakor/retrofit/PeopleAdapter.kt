package com.belajar.drakor.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajar.drakor.databinding.ItemPersonBinding
import com.bumptech.glide.Glide

class PeopleAdapter(private val people: List<Person>) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(people[position])
    }

    override fun getItemCount(): Int = people.size

    class PeopleViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.person = person
            binding.executePendingBindings()

            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${person.profile_path}")
                .into(binding.imageViewProfile)
        }
    }
}
