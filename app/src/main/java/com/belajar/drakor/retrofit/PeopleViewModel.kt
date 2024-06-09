package com.belajar.drakor.retrofit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class PeopleViewModel : ViewModel() {

    fun getPopularPeople() = liveData(Dispatchers.IO) {
        try {
            val response = ApiClient.instance.getPopularPeople("en-US", 1)
            if (response.results.isNotEmpty()) {
                emit(response.results)
                Log.e("PeopleViewModel", "Fetched people list successfully")
            } else {
                Log.e("PeopleViewModel", "Received empty people list from API response")
            }
        } catch (e: Exception) {
            Log.e("PeopleViewModel", "Error fetching popular people", e)
            emit(null)
        }
    }
}
