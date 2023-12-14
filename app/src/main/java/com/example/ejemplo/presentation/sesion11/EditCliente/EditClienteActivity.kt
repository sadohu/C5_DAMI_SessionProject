package com.example.ejemplo.presentation.sesion11.EditCliente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ejemplo.R
import com.example.ejemplo.databinding.ActivityClienteBinding
import com.example.ejemplo.databinding.ActivityEditClienteBinding
import com.example.ejemplo.databinding.ActivityEditProveedorBinding
import com.example.ejemplo.domain.entity.Cliente
import com.example.ejemplo.presentation.sesion11.ClienteViewModel

class EditClienteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditClienteBinding
    private lateinit var viewModel : ClienteViewModel

    var idCliente : Int = 0
    var razonSocial : String = ""
    var direccion : String = ""
    var telefono : String = ""
    var idCategoriaCliente : Int = 0
    var nombreCategoria : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }

    private fun initValues() {
        viewModel = ViewModelProvider(this).get(ClienteViewModel::class.java)

        // Paso para EDITAR
        idCliente = intent.getIntExtra("idCliente", 0)
        razonSocial = intent.getStringExtra("razonSocial")!!
        direccion = intent.getStringExtra("direccion")!!
        telefono = intent.getStringExtra("telefono")!!
        idCategoriaCliente = intent.getIntExtra("idCategoriaCliente", 0)
        nombreCategoria = intent.getStringExtra("nombreCategoria")!!

        binding.edtRazonSocial.setText(razonSocial)
        binding.edtDireccion.setText(direccion)
        binding.edtTelefono.setText(telefono)
        binding.edtCategoria.setText(nombreCategoria)

        binding.btnEditar.setOnClickListener {
            var cliente = Cliente()
            cliente.idCliente = idCliente
            cliente.idCategoriaCliente = idCategoriaCliente
            cliente.razonSocial = binding.edtRazonSocial.text.toString()
            cliente.direccion = binding.edtDireccion.text.toString()
            cliente.telefono = binding.edtTelefono.text.toString()

            viewModel.updateCliente(cliente)
        }
    }

    private fun initObservers() {
        viewModel.updateCliente.observe(this){
            mensajeEditar(it)
        }
    }

    private fun mensajeEditar(mensaje : String){
        AlertDialog.Builder(this)
            .setTitle("Editar Cliente")
            .setMessage(mensaje)
            .setNeutralButton("Ok"){dialog,_->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}