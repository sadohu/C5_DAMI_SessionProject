package com.example.ejemplo.presentation.sesion06.Proveedores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo.R
import com.example.ejemplo.data.room.CibertecDB
import com.example.ejemplo.data.room.entity.ProveedorDB
import com.example.ejemplo.databinding.ActivityProveedorBinding
import kotlinx.android.synthetic.main.dialog_general.view.rcv
import kotlinx.android.synthetic.main.dialog_general.view.txtTitulo

class ProveedorActivity : AppCompatActivity(), ProveedorDBAdapter.IProveedor {
    private lateinit var binding : ActivityProveedorBinding
    private val proveedorDBAdapter by lazy {ProveedorDBAdapter(ArrayList(), this)}
    private lateinit var proveedorDialog : AlertDialog
    private lateinit var proveedorView : View
    private var lstProveedor : MutableList<ProveedorDB> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProveedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initValues()
    }

    private fun initValues(){
        lstProveedor.add(ProveedorDB(1, "Bolsas del Norte", "Av. Larco", "999777111", 1))
        lstProveedor.add(ProveedorDB(2, "Lalaland", "Av. Benavides", "999777111", 2))
        lstProveedor.add(ProveedorDB(3, "Riport mi tim", "Av. Hattin", "999777111", 1))
        var database = CibertecDB.getInstancia(this)
        database.proveedorDao().insert(lstProveedor)
        var listaProveedores = database.proveedorDao().getAll()
        binding.edtProveedor.setOnClickListener{
            proveedorDialog = AlertDialog.Builder(this).create()
            proveedorDBAdapter.update(lstProveedor)
            dialogProveedor()
        }
    }

    fun dialogProveedor(){
        val viewPool = RecyclerView.RecycledViewPool()
        proveedorView = View.inflate(this, R.layout.dialog_general, null)
        proveedorView.rcv.setHasFixedSize(true)
        proveedorView.rcv.layoutManager = LinearLayoutManager(this)
        proveedorView.rcv.adapter = proveedorDBAdapter
        proveedorView.rcv.setRecycledViewPool(viewPool)
        proveedorView.rcv.recycledViewPool.setMaxRecycledViews(0,0)
        proveedorView.txtTitulo.text = "Proveedores"

        proveedorDialog.setView(proveedorView)
        proveedorDialog.show()
    }

    override fun onProveedorClick(item: ProveedorDB) {
        proveedorDialog.dismiss()
        binding.edtProveedor.setText(item.razonSocial)
    }

    override fun onProveedorLongClick(item: ProveedorDB) {
        TODO("Not yet implemented")
    }
}