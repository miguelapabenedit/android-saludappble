package com.example.saludappble.room.dao

import androidx.room.*
import com.example.saludappble.entities.Usuario

@Dao
interface UsuarioDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(usuario: Usuario):Long
    @Update
    fun update(usuario: Usuario)
    @Delete
    fun delete(usuario: Usuario)
    @Query("DELETE FROM usuario")
    fun deleteAll()
    @Query("SELECT * FROM usuario")
    fun getAll():List<Usuario>
    @Query("SELECT * FROM usuario WHERE usuario =:nombreUsuario")
    fun get(nombreUsuario: String):Usuario?
}