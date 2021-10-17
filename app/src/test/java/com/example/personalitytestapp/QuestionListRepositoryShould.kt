package com.example.personalitytestapp

import com.example.personalitytestapp.model.Data
import com.example.personalitytestapp.network.QuestionListService
import com.example.personalitytestapp.repository.QuestionListRepository
import com.example.personalitytestapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class QuestionListRepositoryShould : BaseUnitTest() {

    private val service: QuestionListService = mock()
    private val questionsList = mock<Data>()
    private val exception  = RuntimeException("Something went wrong")


    @Test
    fun getQuestionListFromService() = runBlockingTest{
      val repository = QuestionListRepository(service)
      repository.getQuestionsList()
      verify(service, times(1)).fetchQuestionList()
    }

    @Test
    fun emitPlayListsFromService() = runBlockingTest {
        whenever(service.fetchQuestionList()).thenReturn(
            flow{
                emit(Result.success(questionsList))
            }
        )
        val repository = QuestionListRepository(service)
        assertEquals(questionsList, repository.getQuestionsList().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        mockFailureCase()
        val repository = QuestionListRepository(service)
        assertEquals(exception, repository.getQuestionsList().first().getOrNull())
    }

    private fun mockFailureCase() {
        whenever(service.fetchQuestionList()).thenReturn(
            flow {
                emit(Result.failure<Data>(exception))
            }
        )
    }
}