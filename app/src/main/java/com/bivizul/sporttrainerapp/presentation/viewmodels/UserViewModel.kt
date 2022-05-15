package com.bivizul.sporttrainerapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.sporttrainerapp.data.repository.UserInfoRepositoryImpl
import com.bivizul.sporttrainerapp.domain.user.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(application: Application) : ViewModel() {

    private val repository = UserInfoRepositoryImpl(application)

    private val getUserDataUseCase = GetUserDataUseCase(repository)
    private val setUserDataUseCase = SetUserDataUseCase(repository)
    private val deleteUserDataUseCase = DeleteUserDataUseCase(repository)

    private val getUserDataInfoUseCase = GetUserDataInfoUseCase(repository)

    val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    val userDataInfo = getUserDataInfoUseCase.getUserDataInfo()

    fun setPersonalData(user: User) {
        viewModelScope.launch {
            setUserDataUseCase.setUserData(user)
            _user.postValue(user)
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            _user.value = getUserDataUseCase.getUserData()
        }
    }

    fun deleteUser(userName: String) {
        viewModelScope.launch {
            deleteUserDataUseCase.deleteUserData(userName)
        }
    }

    fun calculation(distance: Int, squat: Int, weight: Int) =
        ((distance + squat * 10) * weight) / 10

}