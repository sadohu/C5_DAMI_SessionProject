package com.example.ejemplo.presentation.sesion09

class SegundoHilo {
    interface DescargarListener{
        fun onDescargarFinalizada()
    }

    public fun descargarArchivo(descargarListener: DescargarListener){
        var thread = Thread(
                Runnable {
                    println("Empieza la descarga del archivo")
                    Thread.sleep(5000)
                    println("Termina la descarga del archivo")
                    descargarListener.onDescargarFinalizada()
                    }
                )
        thread.start()
    }
}