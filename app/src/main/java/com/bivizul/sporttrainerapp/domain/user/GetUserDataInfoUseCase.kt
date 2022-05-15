package com.bivizul.sporttrainerapp.domain.user

import androidx.lifecycle.LiveData

class GetUserDataInfoUseCase(private val userInfoRepository: UserInfoRepository) {

    fun getUserDataInfo(): LiveData<User> {
        return userInfoRepository.getUserInfo()
    }

}