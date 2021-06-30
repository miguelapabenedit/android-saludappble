package com.example.saludappble.application.modules.entities

import com.example.saludappble.repositories.UsuarioRepository
import com.example.saludappble.room.AppDatabase
import com.example.saludappble.room.repository.UsuarioRepositoryRoomImpl
import org.koin.dsl.module

val usuarioModule = module {
    single<UsuarioRepository>(override = true) {
        UsuarioRepositoryRoomImpl(
            get()
        )
    }
    single { get<AppDatabase>().usuarioDao() }
}