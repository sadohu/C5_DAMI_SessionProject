package com.example.ejemplo.presentation.sesion08.EditProveedor

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
import com.example.ejemplo.databinding.ActivityEditProveedorBinding
import com.example.ejemplo.presentation.sesion08.PaisAdapter
import kotlinx.android.synthetic.main.dialog_general.view.rcv
import kotlinx.android.synthetic.main.dialog_general.view.txtTitulo

class EditProveedorActivity : AppCompatActivity(), PaisAdapter.IPais {
    lateinit var binding : ActivityEditProveedorBinding

    private lateinit var database : CibertecDB
    private lateinit var paisAdapter : PaisAdapter

    private lateinit var paisDialog : AlertDialog
    private lateinit var paisView : View

    private var idPais = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProveedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initValues()
    }

    private fun initValues(){
        var idProveedor = intent.getIntExtra("idProveedor", 0)
        database = CibertecDB.getInstancia(this)
        paisAdapter = PaisAdapter(database.paisDao().getAll(), this)

        var proveedorDB = database.proveedorDao().serchProveedorById(idProveedor)
        binding.edtRazonSocial.setText(proveedorDB.razonSocial)
        binding.edtDireccion.setText(proveedorDB.direccion)
        binding.edtTelefono.setText(proveedorDB.telefono)
        idPais = proveedorDB.idPais
        binding.edtPais.setText(database.paisDao().getPaisById(idPais).nombre)

        binding.edtPais.setOnClickListener{
            paisDialog = AlertDialog.Builder(this).create()
            dialogPais()

        }

        binding.btnGuardar.setOnClickListener {
            var proveedorDB = ProveedorDB()
            proveedorDB.idProveedor = idProveedor
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
            .setTitle("Editar Proveedor")
            .setMessage("El proveedor se editó correctamente")
            .setNeutralButton("Ok"){dialog,_->
                finish()
            }
            .setCancelable(false)
            .show()
    }
}