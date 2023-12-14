package com.example.ejemplo.presentation.sesion08.AddProveedor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo.R
import com.example.ejemplo.data.room.CibertecDB
import com.example.ejemplo.data.room.entity.PaisDB
import com.example.ejemplo.data.room.entity.ProveedorDB
import com.example.ejemplo.databinding.ActivityAddProveedorBinding
import com.example.ejemplo.presentation.sesion08.PaisAdapter
import kotlinx.android.synthetic.main.dialog_general.view.rcv
import kotlinx.android.synthetic.main.dialog_general.view.txtTitulo

class AddProveedorActivity : AppCompatActivity(), PaisAdapter.IPais {
    private lateinit var binding : ActivityAddProveedorBinding

    private lateinit var database : CibertecDB
    private lateinit var paisAdapter : PaisAdapter

    private lateinit var paisDialog : AlertDialog
    private lateinit var paisView : View

    private var idPais = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProveedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initValues()
    }

    private fun initValues() {
        database = CibertecDB.getInstancia(this)
        paisAdapter = PaisAdapter(database.paisDao().getAll(), this)

        binding.edtPais.setOnClickListener{
            paisDialog = AlertDialog.Builder(this).create()
            dialogPais()

        }

        binding.btnGuardar.setOnClickListener {
            var proveedorDB = ProveedorDB()
            proveedorDB.razonSocial = binding.edtRazonSocial.text.toString().trim()
            proveedorDB.direccion = binding.edtDireccion.text.toString().trim()
            proveedorDB.telefono = binding.edtTelefono.text.toString().trim()
            proveedorDB.idPais = idPais

            database.proveedorDao().insertProveedor(proveedorDB)

            mensajeGuardar()
        }
    }

    private fun dialogPais(){
        val viewPool = RecyclerView.RecycledViewPool()
        paisView = View.inflate(this, R.layout.dialog_general, null)
        paisView.rcv.setHasFixedSize(true)
        paisView.rcv.layoutManager = LinearLayoutManager(this)
        paisView.rcv.adapter = paisAdapter
        paisView.rcv.setRecycledViewPool(viewPool)
        paisView.rcv.recycledViewPool.setMaxRecycledViews(0,0)
        paisView.txtTitulo.text = "Paises"

        paisDialog.setView(paisView)
        paisDialog.show()
    }

    override fun onPaisClick(item: PaisDB) {
        paisDialog.dismiss()
        binding.edtPais.setText(item.nombre)
        idPais = item.idPais
    }

    private fun mensajeGuardar(){
        AlertDialog.Builder(this)
            .setTitle("Guadar Proveedor")
            .setMessage("El proveedor se guardó correctamente")
            .setNeutralButton("Ok"){dialog,_->
                finish()
            }
            .setCancelable(false)
            .show()
    }

}