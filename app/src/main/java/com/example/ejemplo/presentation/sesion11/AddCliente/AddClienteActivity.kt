package com.example.ejemplo.presentation.sesion11.AddCliente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.ejemplo.R
import com.example.ejemplo.databinding.ActivityAddClienteBinding
import com.example.ejemplo.domain.entity.Cliente
import com.example.ejemplo.presentation.sesion11.ClienteViewModel
import kotlinx.android.synthetic.main.activity_add_cliente.edtDireccion
import kotlinx.android.synthetic.main.activity_add_cliente.edtRazonSocial

class AddClienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddClienteBinding
    private lateinit var viewModel : ClienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        initObservers()
    }
    private fun initValues() {
        viewModel = ViewModelProvider(this).get(ClienteViewModel::class.java)

        binding.btnGuardar.setOnClickListener {
            var cliente = Cliente()
            cliente.idCategoriaCliente = 1
            cliente.razonSocial = binding.edtRazonSocial.text.toString()
            cliente.direccion = binding.edtDireccion.text.toString()
            cliente.telefono = binding.edtTelefono.text.toString()

            viewModel.registrarCliente(cliente)
        }
    }

    private fun initObservers() {
        viewModel.saveCliente.observe(this){
            mensajeGuardar(it)
        }
    }

    private fun mensajeGuardar(mensaje : String){
        AlertDialog.Builder(this)
            .setTitle("Guadar Cliente")
            .setMessage(mensaje)
            .setNeutralButton("Ok"){dialog,_->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}