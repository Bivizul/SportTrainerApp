package com.bivizul.sporttrainerapp.data.network

import com.bivizul.sporttrainerapp.domain.answer.Question
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getWorkoutInfo(dayOfWeek: String) = apiService.getWorkoutInfo(dayOfWeek)

    suspend fun getAnswer() = apiService.getAnswer()

    suspend fun postQuestion(question: Question) = apiService.postQuestion(question)

}