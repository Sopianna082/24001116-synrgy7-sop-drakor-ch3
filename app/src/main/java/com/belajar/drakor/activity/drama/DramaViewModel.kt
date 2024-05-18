package com.belajar.drakor.activity.drama

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DramaViewModel : ViewModel() {
    private val _dramaList = MutableLiveData<List<Drama>>()
    val dramaList: LiveData<List<Drama>> = _dramaList

    private val _favoriteList = MutableLiveData<List<Drama>>()
    val favoriteList: LiveData<List<Drama>> = _favoriteList

    init {
        //Initialize with sample data
        _dramaList.value = listOf(

        )
        _favoriteList.value = emptyList()
    }

    fun addOrRemoveFavorite(drama: Drama){
        drama.isFavorite = !drama.isFavorite
        if(drama.isFavorite){
            _favoriteList.value = _favoriteList.value?.plus(drama)
        } else {
            _favoriteList.value = _favoriteList.value?.minus(drama)
        }
        //Update the drama list
        _dramaList.value = _dramaList.value?.map {
            if (it.title == drama.title) drama else it
        }
    }
}