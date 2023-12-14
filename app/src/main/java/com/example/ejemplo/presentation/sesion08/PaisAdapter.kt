package com.example.ejemplo.presentation.sesion08

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo.R
import com.example.ejemplo.data.room.entity.PaisDB

class PaisAdapter(
    var items : MutableList<PaisDB>,
    val iPais : IPais
) : RecyclerView.Adapter<PaisAdapter.ViewHolder>(){

    interface IPais{
        fun onPaisClick(item : PaisDB)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val txtItem : TextView = itemView.findViewById(R.id.txtItem)

        override fun onClick(v: View?) {
            iPais.onPaisClick(items[adapterPosition])
        }

        init{
            itemView.setOnClickListener(this)
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
        holder.txtItem.text = items[position].nombre
    }

    fun update(newItems : List<PaisDB>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}