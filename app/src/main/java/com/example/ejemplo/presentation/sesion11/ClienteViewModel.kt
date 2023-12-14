package com.example.ejemplo.presentation.sesion11

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ejemplo.data.repository.ClienteRepository
import com.example.ejemplo.domain.entity.Cliente
import kotlinx.coroutines.launch

class ClienteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = ClienteRepository(application)
    // Los Observables retorna el tipo de respuesta del servidor, ya sea un objeto, lista, String, Int, etc.
    // List
    private val _clientes = MutableLiveData<List<Cliente>>()
    val getClientes : LiveData<List<Cliente>> = _clientes
    // Add
    private val _saveCliente = MutableLiveData<String>()
    val saveCliente : LiveData<String> = _saveCliente
    // Update
    private val _updateCliente = MutableLiveData<String>()
    val updateCliente : LiveData<String> = _updateCliente
    // Update
    private val _deleteCliente = MutableLiveData<String>()
    val deleteCliente : LiveData<String> = _deleteCliente

    fun obtenerClientes() = viewModelScope.launch {
        try {
            val result = repository.getClientes()
            _clientes.postValue(result)

        }catch (e: Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun registrarCliente (cliente : Cliente) = viewModelScope.launch {
        try {
            val result = repository.saveCliente(cliente)
            _saveCliente.postValue(result)
        }catch (e : Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun updateCliente (cliente : Cliente) = viewModelScope.launch {
        try {
            val result = repository.updateCliente(cliente)
            _updateCliente.postValue(result)
        }catch (e : Exception){
            Log.d("Error: ", e.message.toString())
        }
    }

    fun deleteCliente (idCliente : Int) = viewModelScope.launch {
        try {
            val result = repository.deleteCliente(idCliente)
            _deleteCliente.postValue(result)
        }catch (e : Exception){
            Log.d("Error: ", e.message.toString())
        }
    }
}