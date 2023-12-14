package com.example.ejemplo.presentation.sesion11

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejemplo.databinding.ActivityClienteBinding
import com.example.ejemplo.domain.entity.Cliente
import com.example.ejemplo.presentation.sesion08.EditProveedor.EditProveedorActivity
import com.example.ejemplo.presentation.sesion11.AddCliente.AddClienteActivity
import com.example.ejemplo.presentation.sesion11.EditCliente.EditClienteActivity

class ClienteActivity : AppCompatActivity(), ClienteAdapter.ICliente {
    lateinit var binding : ActivityClienteBinding
    private var lstClientes : MutableList<Cliente> = ArrayList()
    private lateinit var clienteAdapter: ClienteAdapter

    //
    private lateinit var viewModel: ClienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
        //
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.obtenerClientes()
    }

    private fun initValues(){
        //Inicializar viewModel
        viewModel = ViewModelProvider(this).get(ClienteViewModel::class.java)
        // viewModel = ViewModelProvider(this)[ClienteViewModel::class.java]
        clienteAdapter = ClienteAdapter(lstClientes, this)
        binding.rvClientes.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvClientes.adapter = clienteAdapter

        binding.btnAdd.setOnClickListener{
            startActivity(Intent(this, AddClienteActivity::class.java))
        }

    }

    private fun initObservers(){
        viewModel.getClientes.observe(this){
            clienteAdapter.update(it)
        }

        viewModel.deleteCliente.observe(this){
            viewModel.obtenerClientes()
        }

        viewModel.obtenerClientes()

    }

    override fun onClienteClick(item: Cliente) {
        startActivity(Intent(this, EditClienteActivity::class.java).apply {
            putExtra("idCliente", item.idCliente)
            putExtra("idCategoriaCliente", item.idCategoriaCliente)
            putExtra("razonSocial", item.razonSocial)
            putExtra("direccion", item.direccion)
            putExtra("telefono", item.telefono)
            putExtra("nombreCategoria", item.nombreCategoria)
        })
    }

    override fun onClienteLongClick(item: Cliente) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Proveedor")
            .setMessage("¿Deseas eliminar el cliente ${item.razonSocial}?")
            .setPositiveButton("Sí"){ _, _ ->
                viewModel.deleteCliente(item.idCliente)
            }
            .setNegativeButton("No"){ _, _ ->
            }
            .setCancelable(false)
            .show()
    }
}