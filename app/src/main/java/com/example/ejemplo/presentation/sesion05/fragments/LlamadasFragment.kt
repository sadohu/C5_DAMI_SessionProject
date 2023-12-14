package com.example.ejemplo.presentation.sesion05.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import com.example.ejemplo.R
import com.example.ejemplo.presentation.sesion08.ListProveedorActivity

class LlamadasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_llamadas, container, false)
        initValues(view)

        return view
    }

    private fun initValues(view : View) {
        val tvLlamadas = view.findViewById<TextView>(R.id.tvLlamadas)
        tvLlamadas.setOnClickListener {
            startActivity(Intent(requireContext(), ListProveedorActivity::class.java))
        }
    }
}