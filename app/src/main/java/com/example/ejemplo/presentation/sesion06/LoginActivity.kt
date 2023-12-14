package com.example.ejemplo.presentation.sesion06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ejemplo.MainActivity
import com.example.ejemplo.data.preference.PrefUsuario
import com.example.ejemplo.databinding.ActivityLoginBinding
import com.example.ejemplo.domain.entity.Usuario

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PrefUsuario.getPrefUsuario(applicationContext) != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvents()
    }

    private fun initEvents(){
        binding.btnIngresar.setOnClickListener{
            if(!validarFormulario())
                return@setOnClickListener
            // Logica de iniciar sesion
            // Servicio web se encargara de validar el login
            var usuario = Usuario()
            usuario.idUsuario = 1
            usuario.nombreCompleto = "Hugo Salas"
            usuario.login = "hsalas"
            usuario.edad = 29
            if (PrefUsuario.setPrefUsuario(applicationContext, usuario) == 1){
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
                finish()
            }else{
                Toast.makeText(this, "Sucedio un error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validarFormulario() : Boolean {
        if(binding.etUsuario.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Debe ingresar un Usuario.", Toast.LENGTH_LONG).show()
            return false
        }
        if(binding.etPassword.text.toString().isNullOrEmpty()){
            Toast.makeText(this, "Debe ingresar un Password.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}