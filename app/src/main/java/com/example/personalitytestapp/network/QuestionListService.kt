package com.example.personalitytestapp.network

import com.example.personalitytestapp.model.Data
import com.example.personalitytestapp.model.QuestionList
import com.example.personalitytestapp.model.QuestionsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class QuestionListService (
     private val api: QuestionListApi) {

     fun fetchQuestionList() : Flow<Result<Data>> {
          return flow {
                 emit(Result.success(api.fetchAllQuestionList()))
          }.catch {
               emit(Result.failure(RuntimeException("Something went wrong")))
          }
     }
}