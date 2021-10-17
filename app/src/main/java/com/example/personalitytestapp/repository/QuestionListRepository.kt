package com.example.personalitytestapp.repository

import com.example.personalitytestapp.model.Data
import com.example.personalitytestapp.model.QuestionList
import com.example.personalitytestapp.model.QuestionsItem
import com.example.personalitytestapp.network.QuestionListService
import kotlinx.coroutines.flow.Flow

 class QuestionListRepository (
    private val service: QuestionListService ) {

    suspend fun getQuestionsList() : Flow<Result<Data>> = service.fetchQuestionList()

}