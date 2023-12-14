package com.example.ejemplo.presentation.sesion11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo.R
import com.example.ejemplo.domain.entity.Cliente

class ClienteAdapter (
    var items : MutableList<Cliente>,
    var iCliente : ICliente
) : RecyclerView.Adapter<ClienteAdapter.ViewHolder>(){

    /* Clases internas, necesarias para las herencias */
    interface ICliente {
        fun onClienteClick(item : Cliente)
        fun onClienteLongClick(item: Cliente)
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener{

        val tvRazonSocial : TextView = itemView.findViewById(R.id.tvRazonSocial)
        val tvDireccion : TextView = itemView.findViewById(R.id.tvDireccion)
        val tvTelefono : TextView = itemView.findViewById(R.id.tvTelefono)
        val tvCategoria : TextView = itemView.findViewById(R.id.tvCategoria)
        /* Agregar Listener */
        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            iCliente.onClienteClick(items[adapterPosition])
        }

        override fun onLongClick(v: View?): Boolean {
            iCliente.onClienteLongClick(items[adapterPosition])
            return true
        }
    }

    /* Metodos del RecyclerView */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_cliente, parent, false)
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
        holder.tvCategoria.text = item.nombreCategoria
    }
    /* Actualizar la Lista de Proveedores */
    fun update(newItems : List<Cliente>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}