package com.belajar.drakor.data.datasource.remote.model

import com.belajar.drakor.data.datasource.remote.model.response.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("language") language: String,
        @Query("page") page: Int
    ): PeopleResponse
}
