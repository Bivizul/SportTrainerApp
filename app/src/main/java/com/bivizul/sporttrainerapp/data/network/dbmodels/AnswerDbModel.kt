package com.bivizul.sporttrainerapp.data.network.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class AnswerDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val response: String,
    val ask: String,

    )
