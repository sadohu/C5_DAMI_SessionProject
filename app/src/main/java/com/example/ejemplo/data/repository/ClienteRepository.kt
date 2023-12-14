package com.example.ejemplo.data.repository

import android.app.Application
import com.example.ejemplo.data.room.entity.ProveedorDB
import com.example.ejemplo.domain.entity.Categoria
import com.example.ejemplo.domain.entity.Cliente

class ClienteRepository (application: Application) : BaseRepository(application){

//    suspend fun getClientes() : List<Cliente>{
//        return apiClient.getClientes()
//    }

    suspend fun getClientes() = apiClient.getClientes()

//    suspend fun getCategorias() : List<Categoria>{
//        return apiClient.getCategorias()
//    }

    suspend fun getCategorias() = apiClient.getCategorias()

//    suspend fun getProveedores() : List<ProveedorDB>{
//        return database.proveedorDao().getAll()
//    }

    suspend fun saveCliente(cliente : Cliente) = apiClient.saveCliente(cliente)

    // suspend fun getProveedores() = database.proveedorDao().getAll()

    suspend fun updateCliente(cliente : Cliente) = apiClient.updateCliente(cliente)

    suspend fun deleteCliente(idCliente : Int) = apiClient.deleteCliente(idCliente)
}