package com.bivizul.sporttrainerapp.data.network.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info_all")
data class UserDbModel(
    @PrimaryKey
    val name: String,
    val height: Int,
    val weight: Int,
    val progress: Int,
    val distance: Int,
    val squats: Int,
    val date: String,
)
