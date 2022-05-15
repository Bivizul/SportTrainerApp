package com.bivizul.sporttrainerapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bivizul.sporttrainerapp.data.database.UserDatabase
import com.bivizul.sporttrainerapp.data.mapper.UserDataMapper
import com.bivizul.sporttrainerapp.domain.user.User
import com.bivizul.sporttrainerapp.domain.user.UserInfoRepository

class UserInfoRepositoryImpl(application: Application) : UserInfoRepository {

    private val userInfoDao = UserDatabase.getInstance(application).userInfoDao()
    private val mapper = UserDataMapper()

    override suspend fun setUser(user: User) {
        userInfoDao.setUserData(mapper.mapEntityToDbModel(user))
    }

    override suspend fun getUser(): User {
        return userInfoDao.getUserData()
    }

    override fun getUserInfo(): LiveData<User> =
        Transformations.map(userInfoDao.getUserDataInfo()) {
            mapper.mapDbModelToEntity(it)
        }

    override suspend fun deleteUser(userName: String) {
        userInfoDao.deleteUserData(userName)
    }
}