package com.example.saludappble.application.modules

import androidx.room.Room
import com.example.saludappble.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "suludappble-db").fallbackToDestructiveMigration().build()
    }
}