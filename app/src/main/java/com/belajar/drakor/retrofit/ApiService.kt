package com.belajar.drakor.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("language") language: String,
        @Query("page") page: Int
    ): PeopleResponse
}
