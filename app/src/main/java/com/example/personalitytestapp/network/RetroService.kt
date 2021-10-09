package com.example.personalitytestapp.network

import com.example.personalitytestapp.model.QuestionList
import retrofit2.http.GET

interface RetroService {

    @GET("personality_test")
    suspend fun getDataFromApi() : QuestionList
}