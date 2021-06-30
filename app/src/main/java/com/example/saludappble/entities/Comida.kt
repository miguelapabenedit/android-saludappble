package com.example.saludappble.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "comida",
    indices = [ Index(value = ["usuario","tipoComida","fecha"], unique = true) ])
data class Comida(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var usuario : String,
    var tipoComida : String,
    var comidaPrincipal : String,
    var comidaSecundaria : String,
    var bebida : String,
    var comidaPostre : String,
    var comidaTentacion : String,
    var hambre : Boolean,
    var fecha : Long
)
