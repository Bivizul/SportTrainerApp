package com.bivizul.sporttrainerapp.domain.user

class DeleteUserDataUseCase(private val userInfoRepository: UserInfoRepository) {

    suspend fun deleteUserData(userName: String) {
        userInfoRepository.deleteUser(userName)
    }
}