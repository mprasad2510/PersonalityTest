package com.example.personalitytestapp.network

import com.example.personalitytestapp.model.Data
import com.example.personalitytestapp.model.QuestionsItem
import com.example.personalitytestapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class QuestionListServiceShould : BaseUnitTest() {

    private lateinit var service: QuestionListService
    private val questionsList: Data = mock()
    private val exception = RuntimeException("Something went wrong")
    private val api: QuestionListApi = mock()


    @Test
    fun fetchQuestionListsFromAPI() = runBlockingTest {
        service = QuestionListService(api)
        service.fetchQuestionList().first()
        verify(api, times(1)).fetchAllQuestionList()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runBlockingTest {
        mockSuccessfulCase()
        assertEquals(Result.success(questionsList), service.fetchQuestionList().first())

    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllQuestionList()).thenReturn(questionsList)
        service = QuestionListService(api)
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailureCase()
        val service = QuestionListService(api)
        assertEquals(exception, service.fetchQuestionList().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchAllQuestionList()).thenThrow(
            RuntimeException("Dam Backend Dev")
        )
    }
}