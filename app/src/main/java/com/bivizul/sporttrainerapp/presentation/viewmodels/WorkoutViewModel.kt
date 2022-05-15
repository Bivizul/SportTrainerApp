package com.bivizul.sporttrainerapp.presentation.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.sporttrainerapp.data.network.ApiRepository
import com.bivizul.sporttrainerapp.domain.workout.WorkoutInfo
import com.bivizul.sporttrainerapp.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _workout = MutableLiveData<List<WorkoutInfo>>()
    val workout: LiveData<List<WorkoutInfo>>
        get() = _workout

    @SuppressLint("SimpleDateFormat")
    val dayOfWeekEng = SimpleDateFormat("EEEE").format(Date()).lowercase()
    val dayOfWeekRus = when (dayOfWeekEng) {
        "monday" -> "ПОНЕДЕЛЬНИК"
        "tuesday" -> "ВТОРНИК"
        "wednesday" -> "СРЕДА"
        "thursday" -> "ЧЕТВЕРГ"
        "friday" -> "ПЯТНИЦА"
        "saturday" -> "СУББОТА"
        "sunday" -> "ВОСКРЕСЕНЬЕ"
        else -> ""
    }

    fun getWorkoutInfo() {
        viewModelScope.launch {
            Log.d(TAG, "dayOfWeekEng: $dayOfWeekEng")
            repository.getWorkoutInfo(dayOfWeekEng).let {
                if (it.isSuccessful) {
                    _workout.postValue(it.body())
                } else {
                    Log.d(TAG, "Failed to load WorkoutInfo: ${it.errorBody()}")
                }
            }
        }
    }
}