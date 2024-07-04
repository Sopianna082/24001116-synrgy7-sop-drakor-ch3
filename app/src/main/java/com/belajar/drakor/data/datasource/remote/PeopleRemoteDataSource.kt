package com.belajar.drakor.data.datasource.remote

import com.belajar.drakor.data.datasource.remote.model.response.PeopleResponse

class PeopleRemoteDataSource(private val apiService: ApiService) {
    suspend fun getPopularPeople(language: String, page: Int): PeopleResponse {
        return apiService.getPopularPeople(language, page)
    }
}
