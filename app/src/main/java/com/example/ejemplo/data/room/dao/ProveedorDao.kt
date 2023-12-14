package com.example.ejemplo.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ejemplo.data.room.entity.ProveedorDB

@Dao
interface ProveedorDao {
    @Query("SELECT * FROM ProveedorDB")
    fun getAll() : MutableList<ProveedorDB>

    @Query("SELECT * FROM ProveedorDB WHERE razonSocial = :razonSocial")
    fun serchProveedor(razonSocial : String) : MutableList<ProveedorDB>

    @Query("SELECT * FROM ProveedorDB WHERE idProveedor = :idProveedor")
    fun serchProveedorById(idProveedor : Int) : ProveedorDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProveedor(proveedor : ProveedorDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(proveedores : MutableList<ProveedorDB>)

    @Query("DELETE FROM ProveedorDB")
    fun deleteAll()

    @Query("DELETE FROM ProveedorDB WHERE idProveedor = :idProveedor")
    fun deleteProveedor(idProveedor : Int)

}