package com.bivizul.sporttrainerapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bivizul.sporttrainerapp.data.dbmodels.AnswerDbModel
import com.bivizul.sporttrainerapp.domain.answer.Answer

@Dao
interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setAnswer(answerDbModel: AnswerDbModel)

    @Query("SELECT * FROM answers")
    suspend fun getAnswer(): List<Answer>

    @Query("DELETE FROM answers WHERE response = :response")
    suspend fun deleteAnswer(response: String)

}