package com.bivizul.sporttrainerapp.domain.user

class SetUserDataUseCase(private val userInfoRepository: UserInfoRepository) {

    suspend fun setUserData(user: User) {
        userInfoRepository.setUser(user)
    }

}