package com.bivizul.sporttrainerapp.data.mapper

import com.bivizul.sporttrainerapp.data.network.dbmodels.AnswerDbModel
import com.bivizul.sporttrainerapp.domain.answer.Answer

class AnswerMapper {

    fun mapEntityToDbModel(answer: Answer) = AnswerDbModel(
        id = answer.id,
        ask = answer.ask,
        response = answer.response
    )

    fun mapDbModelToEntity(answerDbModel: AnswerDbModel) = Answer(
        response = answerDbModel.response
    )
}