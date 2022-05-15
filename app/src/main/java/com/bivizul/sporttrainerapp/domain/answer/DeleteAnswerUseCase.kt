package com.bivizul.sporttrainerapp.domain.answer

class DeleteAnswerUseCase(private val answerRepository: AnswerRepository) {

    suspend fun deleteAnswer(answerResponse: String) {
        answerRepository.deleteAnswer(answerResponse)
    }
}