package com.example.ejemplo.presentation.sesion08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejemplo.R
import com.example.ejemplo.data.room.CibertecDB
import com.example.ejemplo.data.room.entity.PaisDB
import com.example.ejemplo.data.room.entity.ProveedorDB
import com.example.ejemplo.databinding.ActivityListProveedorBinding
import com.example.ejemplo.presentation.sesion08.AddProveedor.AddProveedorActivity
import com.example.ejemplo.presentation.sesion08.EditProveedor.EditProveedorActivity

class ListProveedorActivity : AppCompatActivity(), ListProveedorAdapter.IProveedor {
    private lateinit var binding : ActivityListProveedorBinding
    private var lstProveedores : MutableList<ProveedorDB> = ArrayList()
    private var lstPaises : MutableList<PaisDB> = ArrayList()
    private lateinit var database : CibertecDB
    private lateinit var proveedorAdapter : ListProveedorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProveedorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    override fun onResume() {
        super.onResume()
        proveedorAdapter.update(database.proveedorDao().getAll())
    }

    private fun initValues(){
        lstProveedores.add(ProveedorDB(1, "Bolsas del Norte", "Av. Larco", "999777111", 2))
        lstProveedores.add(ProveedorDB(2, "Lalaland", "Av. Benavides", "999777111", 1))
        lstProveedores.add(ProveedorDB(3, "Riport mi tim", "Av. Hattin", "999777111", 1))

        lstPaises.add(PaisDB(1, "Peru"))
        lstPaises.add(PaisDB(2, "Brazil"))

        database = CibertecDB.getInstancia(this)
        database.paisDao().insert(lstPaises)
        database.proveedorDao().insert(lstProveedores)

        /* Late Init proveedorAdapter OJO!!! */
        proveedorAdapter = ListProveedorAdapter(database.proveedorDao().getAll(), this)
        /* Crear el Adapter !!! */
        binding.rvProveedores.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvProveedores.adapter = proveedorAdapter

        binding.btnAdd.setOnClickListener{
            startActivity(Intent(this, AddProveedorActivity::class.java))
        }

    }

    override fun onProveedorClick(proveedorDB: ProveedorDB) {
//        Toast.makeText(this, "Editar el proveedor: " + proveedorDB.razonSocial, Toast.LENGTH_LONG).show()
        startActivity(Intent(this,EditProveedorActivity::class.java).apply {
            putExtra("idProveedor", proveedorDB.idProveedor)
        })
    }

    override fun onProveedorLongClick(proveedorDB: ProveedorDB) {
        //Toast.makeText(this, "Eliminar el proveedor: " + proveedorDB.razonSocial, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle("Eliminar Proveedor")
            .setMessage("¿Deseas eliminar el proveedor ${proveedorDB.razonSocial}?")
            .setPositiveButton("Sí"){ _, _ ->
                database.proveedorDao().deleteProveedor(proveedorDB.idProveedor)
                proveedorAdapter.update(database.proveedorDao().getAll())
            }
            .setNegativeButton("No"){ _, _ ->
            }
            .setCancelable(false)
            .show()
    }
}