package com.example.ejemplo.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProveedorDB (
    @PrimaryKey(autoGenerate = true)
    var idProveedor : Int = 0,
    var razonSocial : String = "",
    var direccion : String = "",
    var telefono : String = "",
    // var ruc : String = ""
    //,var email : String = ""
    var idPais : Int = 0
)
