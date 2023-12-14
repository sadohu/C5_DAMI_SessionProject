package com.example.ejemplo.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaisDB (
    @PrimaryKey(autoGenerate = true)
    var idPais : Int = 0,
    var nombre : String = ""
)