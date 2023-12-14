package com.example.ejemplo.data.api

import com.example.ejemplo.domain.entity.Categoria
import com.example.ejemplo.domain.entity.Cliente
import com.example.ejemplo.domain.entity.Proveedor
import com.example.ejemplo.domain.entity.Usuario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiServices {
    @GET("api/Cliente/GetClientes")
    suspend fun getClientes() : List<Cliente>

    @GET("api/Cliente/GetCategorias")
    suspend fun getCategorias() : List<Categoria>

    @GET("api/Cliente/GetClienteById")
    suspend fun getClienteById(@Query("idCliente") idCliente : Int) : Cliente

    @GET("api/Proveedor/GetProveedores")
    suspend fun getProveedor() : List<Proveedor>

    @POST("api/Cliente/AddCliente")
    suspend fun saveCliente(@Body cliente: Cliente) : String

    @PUT("api/Cliente/UpdateCliente")
    suspend fun updateCliente(@Body cliente: Cliente) : String

    @DELETE("api/Cliente/DeleteCliente")
    suspend fun deleteCliente(
        @Query("idCliente")
        idCliente : Int
    ) : String

    @GET("api/Login/Login")
    suspend fun login(@Body usuario: Usuario) : Usuario
}