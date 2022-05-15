package com.bivizul.sporttrainerapp.domain.user

class GetUserDataUseCase(private val userInfoRepository: UserInfoRepository) {

    suspend fun getUserData(): User {
        return userInfoRepository.getUser()
    }
}