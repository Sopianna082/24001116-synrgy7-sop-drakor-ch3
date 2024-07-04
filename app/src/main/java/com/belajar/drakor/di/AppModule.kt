package com.belajar.drakor.di

import androidx.room.Room
import com.belajar.drakor.domain.repository.BlurRepository
import com.belajar.drakor.data.repository.BlurRepositoryImpl
import com.belajar.drakor.activity.blurfoto.BlurViewModel
import com.belajar.drakor.data.datasource.local.room.AppDatabase
import com.belajar.drakor.data.repository.UserRepository
import com.belajar.drakor.activity.auth.viewmodel.AuthViewModel
import com.belajar.drakor.activity.auth.viewmodel.AuthViewModelFactory
import com.belajar.drakor.activity.drama.DramaViewModel
import com.belajar.drakor.domain.repository.UserProfileRepository
import com.belajar.drakor.data.repository.UserProfileRepositoryImpl
import com.belajar.drakor.activity.unggahfoto.UserProfileViewModel
import com.belajar.drakor.data.datasource.local.room.UserRoomDatabase
import com.belajar.drakor.activity.user.UserViewModel
import com.belajar.drakor.data.datasource.local.datastore.UserPreferences
import com.belajar.drakor.domain.usecase.GetPopularPeopleUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Provide the UserProfileRepository
    single<UserProfileRepository> { UserProfileRepositoryImpl(get()) }
    single<BlurRepository> { BlurRepositoryImpl(get()) }

    // Provide the UserProfileViewModel
    viewModel { BlurViewModel(get(), get()) }
    viewModel { UserProfileViewModel(androidApplication(), get()) }

    // Database instance
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "app_database").build()
    }

    // UserDao instance from AppDatabase
    single { get<AppDatabase>().userDao() }

    // UserRepository instance
    single { UserRepository(get()) }

    // ViewModel for AuthViewModel
    viewModel { AuthViewModel(get()) }

    // ViewModel factory for AuthViewModel
    single { AuthViewModelFactory(get()) }

    single { UserPreferences(androidContext()) }
    viewModel { DramaViewModel() }

    single { Room.databaseBuilder(androidApplication(), UserRoomDatabase::class.java, "user_database").build() }

    single { get<UserRoomDatabase>().userDao() }

    single { UserRepository(get()) }

    viewModel { UserViewModel(get()) }

    single { GetPopularPeopleUseCase(get()) }
}