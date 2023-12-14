package com.example.ejemplo.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ejemplo.data.room.entity.PaisDB

@Dao
interface PaisDao {
    @Query("SELECT * FROM PaisDB")
    fun getAll() : MutableList<PaisDB>

    @Query("SELECT * FROM PaisDB WHERE idPais =:idPais")
    fun getPaisById(idPais : Int) : PaisDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPais(pais : PaisDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(paises : MutableList<PaisDB>)

    @Query("DELETE FROM PaisDB")
    fun deleteAll()
}