package com.example.saludappble.room.dao

import androidx.room.*
import com.example.saludappble.entities.Comida

@Dao
interface ComidaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(comida: Comida):Long
    @Update
    fun update(comida: Comida)
    @Delete
    fun delete(comida: Comida)
    @Query("DELETE FROM comida")
    fun deleteAll()
    @Query("SELECT * FROM comida")
    fun getAll():List<Comida>
    @Query("SELECT * FROM comida WHERE usuario =:nombreUsuario AND fecha =:dia ORDER BY id DESC LIMIT 1")
    fun getUltimaComidaDia(nombreUsuario: String, dia: Long):Comida?
    @Query("SELECT * FROM comida WHERE usuario =:nombreUsuario AND fecha =:dia ORDER BY id ASC")
    fun getAllUsuarioFecha(nombreUsuario: String, dia: Long):List<Comida>
}