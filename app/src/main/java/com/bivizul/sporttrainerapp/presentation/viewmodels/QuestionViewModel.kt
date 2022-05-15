package com.bivizul.sporttrainerapp.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.sporttrainerapp.data.network.ApiRepository
import com.bivizul.sporttrainerapp.data.room.repository.AnswerRepositoryImpl
import com.bivizul.sporttrainerapp.domain.answer.*
import com.bivizul.sporttrainerapp.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    application: Application,
) : ViewModel() {

    private val roomRepository = AnswerRepositoryImpl(application)

    private val getAnswerUseCase = GetAnswerUseCase(roomRepository)
    private val setAnswerUseCase = SetAnswerUseCase(roomRepository)
    private val deleteAnswerUseCase = DeleteAnswerUseCase(roomRepository)

    private val _answer = MutableLiveData<Answer>()
    val answer: LiveData<Answer>
        get() = _answer

    private val _answerList = MutableLiveData<List<Answer>>()
    val answerList: LiveData<List<Answer>>
        get() = _answerList

    private val listAnswer = mutableListOf<Answer?>()

    private val answerSUM = Answer()

    fun getAnswer() {
        viewModelScope.launch {
            apiRepository.getAnswer().let {
                if (it.isSuccessful) {
                    answerSUM.response = it.body()!!.response
                    Log.d(TAG, "answerSUMget: $answerSUM")
                    listAnswer.add(answerSUM)
                    _answerList.postValue(listAnswer as List<Answer>)
                    Log.d(TAG, "_answerList1: ${_answerList.value}")
                    setAnswerUseCase.setAnswer(answerSUM)
                    Log.d(TAG, "_answerList2: ${_answerList.value}")
                } else {
                    Log.d(TAG, "Failed to load getAnswer: ${it.errorBody()}")
                }
            }
        }
    }

    fun postQuestion(question: Question) {
        viewModelScope.launch {
            apiRepository.postQuestion(question).let {
                if (it.isSuccessful) {
//                    _answer.postValue(it.body())
                    Log.d(TAG, "question: $question")
                    Log.d(TAG, "postQuestion: $it")
                    answerSUM.ask = question.ask
                    answerSUM.id = question.id
                    Log.d(TAG, "answerSUMpost: $answerSUM")
                } else {
                    Log.d(TAG, "Failed to load postQuestion: ${it.errorBody()}")
                }
            }
        }
    }

    fun getAnswerRoom() {
        viewModelScope.launch {
            _answerList.value = getAnswerUseCase.getAnswer()
        }
    }

    fun deleteAnswerRoom(answerResponse: String) {
        viewModelScope.launch {
            deleteAnswerUseCase.deleteAnswer(answerResponse)
        }
    }

}