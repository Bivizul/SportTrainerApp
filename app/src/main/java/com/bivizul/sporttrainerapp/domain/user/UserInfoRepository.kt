package com.bivizul.sporttrainerapp.domain.user

import androidx.lifecycle.LiveData

interface UserInfoRepository {

    suspend fun setUser(user: User)

    suspend fun getUser(): User

    fun getUserInfo(): LiveData<User>

    suspend fun deleteUser(userName: String)

}