package com.bivizul.sporttrainerapp.domain.answer

class SetAnswerUseCase(private val answerRepository: AnswerRepository) {

    suspend fun setAnswer(answer: Answer) {
        answerRepository.setAnswer(answer)
    }
}