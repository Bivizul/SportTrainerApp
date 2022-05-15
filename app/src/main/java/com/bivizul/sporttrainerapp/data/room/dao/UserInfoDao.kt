package com.bivizul.sporttrainerapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bivizul.sporttrainerapp.data.network.dbmodels.UserDbModel
import com.bivizul.sporttrainerapp.domain.user.User

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM user_info_all")
    fun getUserDataInfo(): LiveData<UserDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setUserData(userDbModel: UserDbModel)

    @Query("SELECT * FROM user_info_all")
    suspend fun getUserData(): User

    @Query("DELETE FROM user_info_all WHERE name = :name")
    suspend fun deleteUserData(name: String)

//    @Query("UPDATE user_info_all SET progress = :progress")
//    suspend fun setProgress(progress : Progress)

//    @Query("UPDATE user_info_all SET userData = :userData")
//    suspend fun setUserData(userData: UserData)

}