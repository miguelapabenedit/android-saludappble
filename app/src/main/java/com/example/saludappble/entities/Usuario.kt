package com.example.saludappble.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "usuario",
    indices = [ Index(value = ["usuario"], unique = true) ])
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var nombre : String,
    var apellido : String,
    var dni : Long,
    var sexo : String,
    var fechaNacimiento : Long,
    var localidad : String,
    var tipoTratamiento : String,
    var usuario : String,
    var password : String
)
