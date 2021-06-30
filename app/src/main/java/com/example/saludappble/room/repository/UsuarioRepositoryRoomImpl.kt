package com.example.saludappble.room.repository

import com.example.saludappble.entities.Usuario
import com.example.saludappble.repositories.UsuarioRepository
import com.example.saludappble.room.dao.UsuarioDAO
import timber.log.Timber

class UsuarioRepositoryRoomImpl(
    private val usuarioDAO: UsuarioDAO
) : UsuarioRepository {

    override suspend fun insert(usuario: Usuario): Boolean {
        var retorno = false
        try {
            val idUsuario = usuarioDAO.insert(usuario)
            retorno = idUsuario > 0L
        }catch (e : Error){
            Timber.e(e)
        }
        return retorno
    }

    override suspend fun update(usuario: Usuario): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun delete(usuario: Usuario): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun get(nombreUsuario: String): Usuario? {
        var retorno : Usuario? = null
        try{
            retorno = usuarioDAO.get(nombreUsuario)
        }catch (e : Error){
            Timber.e(e)
        }
        return retorno
    }

    override suspend fun isUsuario(nombreUsuario: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Usuario> {
        TODO("Not yet implemented")
    }
}