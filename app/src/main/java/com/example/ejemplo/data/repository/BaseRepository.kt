package com.example.ejemplo.data.repository

import android.app.Application
import com.example.ejemplo.data.api.ApiServices
import com.example.ejemplo.data.api.RetrofitInstance
import com.example.ejemplo.data.room.CibertecDB

open class BaseRepository (application: Application){
    val database = CibertecDB.getInstancia(application)

    protected var apiClient : ApiServices = RetrofitInstance.api.create(ApiServices::class.java)

    protected var apiGoogleMaps : ApiServices = RetrofitInstance.apiMaps.create(ApiServices::class.java)
}