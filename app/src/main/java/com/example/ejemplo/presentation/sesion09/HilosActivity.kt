package com.example.ejemplo.presentation.sesion09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemplo.R
import com.example.ejemplo.databinding.ActivityHilosBinding

class HilosActivity : AppCompatActivity(), SegundoHilo.DescargarListener {
    private lateinit var binding : ActivityHilosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHilosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues(){
        binding.btnEjecutar.setOnClickListener {
            //descargarArchivo()
            val segundoHilo = SegundoHilo()
            segundoHilo.descargarArchivo(this)
        }
    }

    private fun descargarArchivo(){
        binding.btnEjecutar.setOnClickListener {
            var thread = Thread(
                Runnable {
                    println("Empieza hilo principal")
                    Thread.sleep(5000)
                    println("Termina hilo principal")
                    runOnUiThread {
                        binding.tvTitulo.setText("Se termino la descarga del archivo")
                    }
                }
            )
            thread.start()
        }
    }

    override fun onDescargarFinalizada() {
        runOnUiThread {
            binding.tvTitulo.setText("Se termino la descarga del archivo")
        }
    }
}