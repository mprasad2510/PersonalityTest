package com.example.personalitytestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalitytestapp.model.QuestionList
import com.example.personalitytestapp.network.RetroInstance
import com.example.personalitytestapp.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionListViewModel : ViewModel() {

    var questionListLiveData : MutableLiveData<QuestionList> = MutableLiveData()

    fun getQuestionListObserver() : MutableLiveData<QuestionList> {
        return questionListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi()
            questionListLiveData.postValue(response)
        }
    }
}