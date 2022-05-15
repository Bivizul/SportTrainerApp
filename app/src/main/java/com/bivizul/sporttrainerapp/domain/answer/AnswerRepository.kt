package com.bivizul.sporttrainerapp.domain.answer

interface AnswerRepository {

    suspend fun setAnswer(answer: Answer)

    suspend fun getAnswer(): List<Answer>

    suspend fun deleteAnswer(answerResponse: String)

}