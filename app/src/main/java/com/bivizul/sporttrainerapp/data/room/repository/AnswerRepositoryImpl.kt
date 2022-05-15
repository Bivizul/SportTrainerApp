package com.bivizul.sporttrainerapp.data.room.repository

import android.app.Application
import com.bivizul.sporttrainerapp.data.room.database.AnswerDatabase
import com.bivizul.sporttrainerapp.data.mapper.AnswerMapper
import com.bivizul.sporttrainerapp.domain.answer.Answer
import com.bivizul.sporttrainerapp.domain.answer.AnswerRepository

class AnswerRepositoryImpl(application: Application) : AnswerRepository {

    private val answerDao = AnswerDatabase.getInstance(application).answerDao()
    private val mapper = AnswerMapper()

    override suspend fun setAnswer(answer: Answer) {
        answerDao.setAnswer(mapper.mapEntityToDbModel(answer))
    }

    override suspend fun getAnswer(): List<Answer> {
        return answerDao.getAnswer()
    }

    override suspend fun deleteAnswer(answerResponse: String) {
        answerDao.deleteAnswer(answerResponse)
    }
}