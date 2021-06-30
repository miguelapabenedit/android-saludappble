package com.example.saludappble.repositories

import com.example.saludappble.entities.Usuario

interface UsuarioRepository{
    suspend fun insert(usuario: Usuario) : Boolean
    suspend fun update(usuario: Usuario) : Boolean
    suspend fun delete(usuario: Usuario) : Boolean
    suspend fun deleteAll() : Boolean
    suspend fun get(nombreUsuario: String) : Usuario?
    suspend fun isUsuario(nombreUsuario: String) : Boolean
    suspend fun getAll(): List<Usuario>

}