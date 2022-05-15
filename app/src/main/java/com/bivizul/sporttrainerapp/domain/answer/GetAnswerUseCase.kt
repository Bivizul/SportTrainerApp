package com.bivizul.sporttrainerapp.domain.answer

class GetAnswerUseCase(private val answerRepository: AnswerRepository) {

    suspend fun getAnswer(): List<Answer> {
        return answerRepository.getAnswer()
    }
}