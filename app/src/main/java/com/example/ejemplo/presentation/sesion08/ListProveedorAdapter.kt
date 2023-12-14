package com.example.ejemplo.presentation.sesion08

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo.R
import com.example.ejemplo.data.room.entity.ProveedorDB

class ListProveedorAdapter (
    var items : MutableList<ProveedorDB>,
    var iProveedor : IProveedor
) : RecyclerView.Adapter<ListProveedorAdapter.ViewHolder>(){

    /* Clases internas, necesarias para las herencias */
    interface IProveedor {
        fun onProveedorClick(proveedorDB : ProveedorDB)
        fun onProveedorLongClick(proveedorDB: ProveedorDB)
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener{

        val tvRazonSocial : TextView = itemView.findViewById(R.id.tvRazonSocial)
        val tvDireccion : TextView = itemView.findViewById(R.id.tvDireccion)
        val tvTelefono : TextView = itemView.findViewById(R.id.tvTelefono)
        /* Agregar Listener */
        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            iProveedor.onProveedorClick(items[adapterPosition])
        }

        override fun onLongClick(v: View?): Boolean {
            iProveedor.onProveedorLongClick(items[adapterPosition])
            return true
        }
    }

    /* Metodos del RecyclerView */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_proveedor, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position] // Obtiene la posicion de los ITEMS
        holder.tvRazonSocial.text = item.razonSocial
        holder.tvDireccion.text = item.direccion
        holder.tvTelefono.text = item.telefono
    }
    /* Actualizar la Lista de Proveedores */
    fun update(newItems : List<ProveedorDB>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}