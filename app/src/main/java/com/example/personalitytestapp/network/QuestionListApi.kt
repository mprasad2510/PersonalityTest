package com.example.personalitytestapp.network

import com.example.personalitytestapp.model.Data
import com.example.personalitytestapp.model.QuestionList
import com.example.personalitytestapp.model.QuestionsItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuestionListApi {

    @GET("personality")
    suspend fun fetchAllQuestionList() : Data
}