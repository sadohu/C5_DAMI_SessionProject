package com.example.ejemplo.data.preference

import android.content.Context
import com.example.ejemplo.domain.entity.Usuario
import com.google.gson.Gson

object PrefUsuario {
    val PREFERENCE_NAME = "PrefUsuario"
    val CLASS_NAME = "Usuario"

    fun setPrefUsuario(context: Context, usuario : Usuario) : Int{
        val pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        val gson = Gson()
        val usuarioJson = gson.toJson(usuario)
        /*
        editor.putString("nombreCompleto", "Hugo Salas")
        editor.putInt("edad", 29)
        */
        editor.putString(CLASS_NAME, usuarioJson)
        editor.apply()
        return 1
    }

    fun getPrefUsuario(context: Context) : Usuario?{
        var usuario : Usuario? = null
        val pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        if(pref.contains(CLASS_NAME)){
            val usuarioJson = pref.getString(CLASS_NAME, "")
            val gson = Gson()
            usuario = gson.fromJson(usuarioJson, Usuario::class.java)
        }
        return usuario
    }

    fun deletePrefUsuario(context: Context){
        val pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}