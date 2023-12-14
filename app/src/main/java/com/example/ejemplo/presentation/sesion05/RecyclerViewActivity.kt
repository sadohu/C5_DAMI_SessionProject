package com.example.ejemplo.presentation.sesion05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejemplo.R
import com.example.ejemplo.databinding.ActivityRecyclerViewBinding
import com.example.ejemplo.domain.entity.Proveedor

class RecyclerViewActivity : AppCompatActivity(), ProveedorAdapter.IClickListener {
    private lateinit var binding : ActivityRecyclerViewBinding
    private var listProveedores : MutableList<Proveedor> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues(){
        listProveedores.add(Proveedor(1,"Bolsas del Norte SAC", "Av. Miraflores 555", "123456789"))
        listProveedores.add(Proveedor(2,"Banco Arequipa SA", "Av. Olivos 444", "014785264"))
        listProveedores.add(Proveedor(3,"SAB Yun SAC", "Av. Monte Olivo 555", "369852147"))

        binding.rvProveedores.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvProveedores.adapter = ProveedorAdapter(listProveedores, this)
    }

    override fun onProveedorClick(item: Proveedor) {
        Log.d("onClick", item.razonSocial)
        Toast.makeText(this, "Ha seleccionado el proveedor ${item.razonSocial}", Toast.LENGTH_LONG).show()
    }

    override fun onProveedorLongClick(item: Proveedor) {
        Log.d("onLogClick", item.razonSocial)
        Toast.makeText(this, "Ha mantenido seleccionado el proveedor ${item.razonSocial}", Toast.LENGTH_LONG).show()
    }

}