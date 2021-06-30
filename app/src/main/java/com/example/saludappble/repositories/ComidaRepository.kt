package com.example.saludappble.repositories

import com.example.saludappble.entities.Comida

interface ComidaRepository{
    suspend fun insert(comida : Comida) : Boolean
    suspend fun update(comida : Comida) : Boolean
    suspend fun delete(comida : Comida) : Boolean
    suspend fun deleteAll() : Boolean
    suspend fun ultimaComidaDia(nombreUsuario : String, dia:Long) : Comida?
    suspend fun getAllUsuarioFecha(nombreUsuario : String, fecha:Long):List<Comida>
}