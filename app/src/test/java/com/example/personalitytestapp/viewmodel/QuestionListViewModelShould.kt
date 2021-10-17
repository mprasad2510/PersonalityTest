package com.example.personalitytestapp.viewmodel

import com.example.personalitytestapp.repository.QuestionListRepository
import com.example.personalitytestapp.model.Data
import com.example.personalitytestapp.utils.BaseUnitTest
import com.example.personalitytestapp.viewmodel.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test
import java.lang.RuntimeException

class QuestionListViewModelShould : BaseUnitTest() {

    private val repository: QuestionListRepository = mock()
    private val questionsLiveData = mock<Data>()
    private val expected = Result.success(questionsLiveData)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getQuestionFromRepository() = runBlockingTest {
        val viewModel = `mockSuccessfulCase()`()

        viewModel.questionListLiveData.getValueForTest()
        verify(repository , times(1)).getQuestionsList()
    }

    @Test
    fun emitsQuestionsFromRepository() = runBlockingTest {
        val viewModel = `mockSuccessfulCase()`()
        assertEquals(expected , viewModel.questionListLiveData.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivedError() {
        runBlockingTest {
            whenever(repository.getQuestionsList()).thenReturn(
                flow {
                    emit(Result.failure<Data>(exception))
                }
            )
        }

        val viewModel = QuestionListViewModel(repository)
        assertEquals(exception, viewModel.questionListLiveData.getValueForTest()!!.exceptionOrNull())
    }

    private fun TestCoroutineScope.`mockSuccessfulCase()`(): QuestionListViewModel {
        runBlockingTest {
            whenever(repository.getQuestionsList()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = QuestionListViewModel(repository)
        return viewModel
    }
}