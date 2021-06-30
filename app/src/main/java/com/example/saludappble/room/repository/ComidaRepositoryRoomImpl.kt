package com.example.saludappble.room.repository

import com.example.saludappble.entities.Comida
import com.example.saludappble.repositories.ComidaRepository
import com.example.saludappble.room.dao.ComidaDAO
import timber.log.Timber

class ComidaRepositoryRoomImpl(
    private val comidaDAO : ComidaDAO
) : ComidaRepository {

    override suspend fun insert(comida: Comida): Boolean {
        var retorno = false
        try {
           val idComida = comidaDAO.insert(comida)
           retorno = idComida > 0L
        }catch (e : Error){
            Timber.e(e)
        }
        return retorno
    }

    override suspend fun update(comida: Comida): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun delete(comida: Comida): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun ultimaComidaDia(nombreUsuario: String, dia: Long): Comida? {
        var retorno : Comida? = null
        try {
            retorno = comidaDAO.getUltimaComidaDia(nombreUsuario, dia)
        }catch (e : Error){
            Timber.e(e)
        }
        return retorno
    }

    override suspend fun getAllUsuarioFecha(nombreUsuario: String, fecha: Long): List<Comida> {
        var retorno : List<Comida> = listOf()
        try {
            retorno = comidaDAO.getAllUsuarioFecha(nombreUsuario, fecha)
        }catch (e : Error){
            Timber.e(e)
        }
        return retorno
    }

}