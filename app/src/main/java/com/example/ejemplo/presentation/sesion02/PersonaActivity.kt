package com.example.ejemplo.presentation.sesion02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemplo.R
import com.example.ejemplo.databinding.ActivityPersonaBinding

class PersonaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_persona)
        binding = ActivityPersonaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtenerVariables()
    }

    fun obtenerVariables(){
        var nombre = intent.getStringExtra("nombre")
        var apePaterno = intent.getStringExtra("apePaterno")
        var edad = intent.getIntExtra("edad",0)
        var nota1 = intent.getDoubleExtra("nota1", 0.0)
        var aprobado = intent.getBooleanExtra("aprobado",false)

        binding.tvDatosPersona.text = nombre + " " + apePaterno + " edad: " + edad

    }


}