package com.example.personalitytestapp.viewmodel

import androidx.lifecycle.*
import com.example.personalitytestapp.repository.QuestionListRepository
import com.example.personalitytestapp.model.Data

class QuestionListViewModel(
    private val repository: QuestionListRepository
) : ViewModel() {

    var questionListLiveData = liveData<Result<Data>> {
        emitSource(repository.getQuestionsList().asLiveData())
    }

//    init {
//        viewModelScope.launch {
//            repository.getQuestionsList()
//                .collect {
//                    questionListLiveData.value = it
//                }
//        }
//    }


//    fun getQuestionListObserver() : MutableLiveData<List<QuestionsItem>> {
//        return questionListLiveData
//    }
//
//
//    fun makeApiCall() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
//            val response = retroInstance.getDataFromApi()
//            questionListLiveData.postValue(response)
//        }
//    }
}