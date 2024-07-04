package com.belajar.drakor.data.di

import com.belajar.drakor.common.NetworkHelper
import com.belajar.drakor.data.datasource.remote.ApiService
import com.belajar.drakor.data.datasource.remote.PeopleRemoteDataSource
import com.belajar.drakor.data.datasource.remote.TMDB_TOKEN
import com.belajar.drakor.data.repository.PeopleRepositoryImpl
import com.belajar.drakor.domain.repository.PeopleRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { NetworkHelper.createRetrofit("https://api.themoviedb.org/3/").create(ApiService::class.java) }
    single { PeopleRemoteDataSource(get()) }
    single<PeopleRepository> { PeopleRepositoryImpl(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
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

