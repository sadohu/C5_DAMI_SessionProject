package com.example.ejemplo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ejemplo.data.preference.PrefUsuario
import com.example.ejemplo.databinding.ActivityMainBinding
import com.example.ejemplo.presentation.sesion02.PersonaActivity
import com.example.ejemplo.presentation.sesion05.RecyclerViewActivity
import com.example.ejemplo.presentation.sesion05.fragments.TabsActivity
import com.example.ejemplo.presentation.sesion06.LoginActivity
import com.example.ejemplo.presentation.sesion06.Proveedores.ProveedorActivity
import com.example.ejemplo.presentation.sesion08.ListProveedorActivity
import com.example.ejemplo.presentation.sesion09.HilosActivity
import com.example.ejemplo.presentation.sesion11.ClienteActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvHolaMundo.text = "Hola mundo de prueba"

        initEvents()
        /*variableConstantes()
        tiposDatos()
        sentenciaIf()
        sentenciaWhen()
        arrays()
        bucles()
        nulos()
        funciones()*/
    }

    fun initEvents(){
        binding.btnPersonaActivity.setOnClickListener{
            Log.d("btnPersonAct","Se realizó un click en el botón btnPersonaActivity")
            startActivity(
                Intent(this, PersonaActivity::class.java).apply {
                    putExtra("nombre","Fabio Alejandro")
                    putExtra("apePaterno","Peralta")
                    putExtra("edad",28)
                    putExtra("nota1",18.5)
                    putExtra("aprobado", true)
                }
            )
        }

        binding.btnRecycler.setOnClickListener{
            startActivity(
                Intent(this, RecyclerViewActivity::class.java).apply {
                }
            )
        }

        binding.btnFragments.setOnClickListener {
            startActivity(
                Intent(this, TabsActivity::class.java).apply {
                }
            )
        }

        binding.btnProveedor.setOnClickListener{
            startActivity(
                Intent(this, ProveedorActivity::class.java).apply {
                }
            )
        }

        binding.btnMantenedorProveedores.setOnClickListener{
            startActivity(
                Intent(this, ListProveedorActivity::class.java).apply {
                }
            )
        }

        binding.btnHilos.setOnClickListener {
            startActivity(Intent(this, HilosActivity::class.java).apply {

            })
        }

        binding.btnMatenedorClientes.setOnClickListener{
            startActivity(
                Intent(this, ClienteActivity::class.java).apply {
                }
            )
        }

        binding.btnCerrarSesion.setOnClickListener{
            PrefUsuario.deletePrefUsuario(applicationContext)
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish()
        }
    }

    fun variableConstantes(){
        // "var" es una variable
        var nombres = "Fabio Alejandro"
        nombres = "Fabio"
        var apellidoPaterno : String = "Peralta Medina"
        println("Nombres: ,$nombres")
        println("Apellidos , $apellidoPaterno")

        // "val" es una CONSTANTE !
        //val edad = 28
        val edad : Int = 28
        println("Edad: $edad")
    }

    fun tiposDatos(){
        //String
        var string1 : String = "Este es el string 1"
        var string2 = "Este es el string 2"
        val string3 = string1 + "-" + string2
        val string4 = "$string1-$string2 : Esto es una concatenación"
        println(string3)
        println(string4)

        //Enteros (Int, Short, Long) referencia JS: int16, int32, int64
        var int1 : Int = 10
        var int2 = 20
        var int3 = int1 + int2
        println(int3)

        //Decimales (Float, Double)
        var float1 : Float = 10.5f
        var double1 : Double = 20.5
        var double2 = 2.6
        var double3 = double1 + double2 + float1 + int1
        println("Double3 : $double3")

        //Boolean
        var bool1 : Boolean = true
        var bool2 = false
        println("$bool1 - $bool2")
        println(bool1 == bool2)
    }

    fun sentenciaIf(){
        /*  Operadores condicionales: ==, >, >=, <, <=, !=
            Operadores logicos: &&, ||, !
         */
        val edad = 35
        if(edad >= 30)
            println("Mi edad es mayor o igual a 30")
        else
            println("Mi edad es menor a 30")

        //if(edad >= 30 && edad <= 50)
        if(edad in 30..50)
            println("Mi edad está entre 30 y 50")
        else
            println("Mi edad es mejor a 30 o mayor a 50")
    }

    fun sentenciaWhen(){
        var nombre = "Hugo :3"
        when(nombre){
            "Fabio"->{
                println("Es Fabio")
            }
            "Jhordan" -> {
                println("Es Jhordan")
            }
            else->{
                println("No es Fabio ni Jhordan")
            }
        }
    }

    fun arrays(){
        var nombre = "Fabio"
        var apePaterno = "Peralta"
        var edad = "28"

        var array : ArrayList<String> = arrayListOf()
        array.add(nombre)
        array.add(apePaterno)
        array.add(edad)
        println(array)
        array.addAll(listOf("Medina", "Cibertec"))
        println(array)
        array.removeAt(2)
        println(array)
    }

    fun bucles(){
        var array : ArrayList<String> = arrayListOf()
        array.addAll(listOf("Blanco", "Negro", "Amarillo", "Rojo", "Azul"))
        for(color: String in array){
            println(color)
        }
        // for(int i = 0; i <= 10; i++) - Forma habitual
        for(i in 0..10){
            println(i)
        }
        println("============")
        for(i in 0..10 step 2){
            println(i)
        }
        println("============")
        // Desendente
        for(i in 10 downTo 0){
            println(i)
        }
        println("============")
        var contador = 1
        while (contador <= 5){
            println("Hola Nº: $contador")
            contador++
        }
    }

    fun nulos(){
        /* Error de compilacion!
        var nombre = "Hugo"
        nombre = null
        println(nombre)*/

        //String?
        var apellido : String? = "Luperdi"
        println(apellido)
        apellido = null
        println(apellido)
        apellido = "Luperdi"
        println(apellido)

        if(apellido != null){
            // Evitar error de compilacion "variable?"
            // en caso no sepamos si el el dato sera nulo
            apellido = null
            println("?: " + apellido?.length)
            // Asegurar que la variable no sera nula "variable!!"
            apellido = "Salas"
            println("!!: " + apellido!!.length)
        }
    }

    // Funciones
    // public void => Sin embargo, declararlo como public será redundante para Kotlin
    public fun funcionPublica(){}

    // Funcion con parametros - public void suma(int numero1, int numero2)
    fun suma(numero1: Int, numero2: Int){
        var suma = numero1 + numero2
        println("La suma: $suma")
    }
    // Sobrecarga
    fun suma(numero1: Int, numero2: Int, numero3: Int){
        var suma = numero1 + numero2 + numero3
        println("La suma: $suma")
    }
    // Funcion con retorno de dato "Int"
    fun suma2(numero1: Int, numero2: Int) : Int {
        return numero1 + numero2
    }

    fun funciones(){
        suma(15,10)
        println("Suma 2: ${suma2(1,2)}")
    }

}