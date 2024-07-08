package com.belajar.drakor.activity.user.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.belajar.drakor.activity.user.UserAdapter
import com.belajar.drakor.data.datasource.local.room.User

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: LiveData<List<User>>?) {
    val adapter = recyclerView.adapter as UserAdapter
    data?.value?.let {
        adapter.submitList(it)
    }
}