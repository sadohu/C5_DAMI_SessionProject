package com.example.ejemplo.presentation.sesion05.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemplo.R
import com.example.ejemplo.databinding.ActivityTabsBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTabsBinding
    private val adapter by lazy {ViewPagerAdapter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_tabs)
        binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initValues()
    }

    private fun initValues(){
        binding.pager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(binding.tabLayout, binding.pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position){
                    0 -> { tab.text = "Chats" }
                    1 -> { tab.text = "Estados"}
                    2 -> { tab.text = "Llamadas" }
                }
            }
        )
        tabLayoutMediator.attach()
    }
}