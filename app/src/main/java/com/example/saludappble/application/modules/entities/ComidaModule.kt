package com.example.saludappble.application.modules.entities

import com.example.saludappble.repositories.ComidaRepository
import com.example.saludappble.room.AppDatabase
import com.example.saludappble.room.repository.ComidaRepositoryRoomImpl
import org.koin.dsl.module

val comidaModule = module {
    single<ComidaRepository>(override = true) {
        ComidaRepositoryRoomImpl(
            get()
        )
    }
    single { get<AppDatabase>().comidaDao() }
}