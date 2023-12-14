package com.example.ejemplo.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ejemplo.R
import com.example.ejemplo.data.room.dao.PaisDao
import com.example.ejemplo.data.room.dao.ProveedorDao
import com.example.ejemplo.data.room.entity.PaisDB
import com.example.ejemplo.data.room.entity.ProveedorDB

@Database(
    entities = [
        ProveedorDB::class,
        PaisDB::class
    ],
    version = 2,
    exportSchema = false
)
abstract class CibertecDB : RoomDatabase(){
    abstract fun proveedorDao() : ProveedorDao
    abstract fun paisDao() : PaisDao

    companion object{
        private var instancia : CibertecDB? = null

        @Synchronized
        fun getInstancia(context: Context) : CibertecDB{
            if(instancia == null){
                instancia = Room.databaseBuilder(
                    context,
                    CibertecDB::class.java,
                    context.getString(R.string.database_name)
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instancia as CibertecDB
        }
    }
}