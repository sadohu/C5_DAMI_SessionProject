package com.example.ejemplo.presentation.sesion05.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fa : FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {ChatsFragment()}
            1 -> {EstadosFragment()}
            2 -> {LlamadasFragment()}
            else -> {ChatsFragment()}
        }
    }
}