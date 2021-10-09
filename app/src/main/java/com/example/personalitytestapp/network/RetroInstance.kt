package com.example.personalitytestapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {
        val BASEURL = "http://localhost:3000/"
        fun getRetroInstance() : Retrofit
        {
            return Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}