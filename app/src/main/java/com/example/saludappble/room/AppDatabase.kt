package com.example.saludappble.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.saludappble.entities.Comida
import com.example.saludappble.entities.Usuario
import com.example.saludappble.room.dao.ComidaDAO
import com.example.saludappble.room.dao.UsuarioDAO

@Database(entities = [
    Usuario::class,
    Comida::class],
        version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao() : UsuarioDAO
    abstract fun comidaDao() : ComidaDAO
}