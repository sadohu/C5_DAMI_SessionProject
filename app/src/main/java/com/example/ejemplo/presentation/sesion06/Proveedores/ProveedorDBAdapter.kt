package com.example.ejemplo.presentation.sesion06.Proveedores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo.R
import com.example.ejemplo.data.room.entity.ProveedorDB

class ProveedorDBAdapter (
    var items : MutableList<ProveedorDB>,
    val clickListener : IProveedor
        ) : RecyclerView.Adapter<ProveedorDBAdapter.ViewHolder>(){

    interface IProveedor{
        fun onProveedorClick(item : ProveedorDB)
        fun onProveedorLongClick(item : ProveedorDB)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener{
//        val tvRazonSocial : TextView = itemView.findViewById(R.id.tvRazonSocial)
//        val tvDireccion : TextView = itemView.findViewById(R.id.tvDireccion)
//        val tvTelefono : TextView = itemView.findViewById(R.id.tvTelefono)
        val txtItem : TextView = itemView.findViewById(R.id.txtItem)

        override fun onClick(v: View?) {
            clickListener.onProveedorClick(items[adapterPosition])
        }

        override fun onLongClick(v: View?): Boolean {
            clickListener.onProveedorLongClick(items[adapterPosition])
            return true
        }

        init{
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_general, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.tvRazonSocial.text = items[position].razonSocial
//        holder.tvDireccion.text = items[position].direccion
//        holder.tvTelefono.text = items[position].telefono
        holder.txtItem.text = items[position].razonSocial
    }

    fun update(newItems : List<ProveedorDB>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}