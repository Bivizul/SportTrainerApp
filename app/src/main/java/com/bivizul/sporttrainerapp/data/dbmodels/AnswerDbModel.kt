package com.bivizul.sporttrainerapp.data.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class AnswerDbModel(
    @PrimaryKey
    val response: String,
)
