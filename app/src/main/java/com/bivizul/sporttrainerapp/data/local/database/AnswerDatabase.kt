package com.bivizul.sporttrainerapp.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bivizul.sporttrainerapp.data.local.dao.AnswerDao
import com.bivizul.sporttrainerapp.data.network.dbmodels.AnswerDbModel

@Database(entities = [AnswerDbModel::class], version = 1, exportSchema = false)
abstract class AnswerDatabase : RoomDatabase() {

    abstract fun answerDao(): AnswerDao

    companion object {

        private val LOCK = Any()

        @Volatile
        private var INSTANCE: AnswerDatabase? = null
        private const val DB_NAME = "answer.db"

        fun getInstance(application: Application): AnswerDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AnswerDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}