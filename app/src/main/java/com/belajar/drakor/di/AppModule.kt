package com.belajar.drakor.di

import androidx.room.Room
import com.belajar.drakor.activity.blurfoto.BlurRepository
import com.belajar.drakor.activity.blurfoto.BlurRepositoryImpl
import com.belajar.drakor.activity.blurfoto.BlurViewModel
import com.belajar.drakor.activity.designpatternmvvm.model.AppDatabase
import com.belajar.drakor.activity.designpatternmvvm.model.UserRepository
import com.belajar.drakor.activity.designpatternmvvm.viewmodel.AuthViewModel
import com.belajar.drakor.activity.designpatternmvvm.viewmodel.AuthViewModelFactory
import com.belajar.drakor.activity.drama.DramaViewModel
import com.belajar.drakor.activity.unggahfoto.UserProfileRepository
import com.belajar.drakor.activity.unggahfoto.UserProfileRepositoryImpl
import com.belajar.drakor.activity.unggahfoto.UserProfileViewModel
import com.belajar.drakor.activity.user.UserRoomDatabase
import com.belajar.drakor.activity.user.UserViewModel
import com.belajar.drakor.datastore.UserPreferences
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
}