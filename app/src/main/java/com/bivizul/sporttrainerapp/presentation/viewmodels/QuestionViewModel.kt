package com.bivizul.sporttrainerapp.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.sporttrainerapp.data.network.ApiRepository
import com.bivizul.sporttrainerapp.data.repository.AnswerRepositoryImpl
import com.bivizul.sporttrainerapp.domain.answer.*
import com.bivizul.sporttrainerapp.utils.Constants
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

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _answerList = MutableLiveData<List<Answer>>()
    val answerList: LiveData<List<Answer>>
        get() = _answerList

    private val listAnswer = mutableListOf<Answer?>()

    fun getAnswer() {
        viewModelScope.launch {
            apiRepository.getAnswer().let {
                if (it.isSuccessful) {
                    listAnswer.add(it.body())
                    _answerList.postValue(listAnswer as List<Answer>)
                    Log.d(TAG, "_answerList: $_answerList")
                    setAnswerUseCase.setAnswer(it.body()!!)
                } else {
                    Log.d(TAG, "Failed to load WorkoutInfo: ${it.errorBody()}")
                }
            }
        }
    }

    fun postQuestion(question: Question) {
        viewModelScope.launch {
            apiRepository.postQuestion(question).let {
                if (it.isSuccessful) {
                    _answer.postValue(it.body())
                } else {
                    Log.d(TAG, "Failed to load WorkoutInfo: ${it.errorBody()}")
                }
            }
        }
    }

    fun setAnswerRoom(answer: Answer) {
        viewModelScope.launch {
            setAnswerUseCase.setAnswer(answer)
            _answer.postValue(answer)
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