package com.belajar.drakor.retrofit

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TMDB_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZmExYzUzNmZmMTcyNTdiODY1ZWI0OTA2YjNiYWU2ZCIsInN1YiI6IjY2NGFiNWFiYTBmNzE0NGU0NDkyYzI3YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-2CdP-b3-_IUbZN8fyJjAw-htE7wUdFR4OS848jW580"

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .client(provideOkHttpClient())
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val authInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $TMDB_TOKEN")
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()
}

object ApiClient {
    val instance: ApiService by lazy {
        provideRetrofit().create(ApiService::class.java)
    }
}