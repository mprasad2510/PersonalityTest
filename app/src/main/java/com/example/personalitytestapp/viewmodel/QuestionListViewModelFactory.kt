package com.example.personalitytestapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.personalitytestapp.repository.QuestionListRepository

class QuestionListViewModelFactory(
    private val repository: QuestionListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuestionListViewModel(repository) as T
    }
}