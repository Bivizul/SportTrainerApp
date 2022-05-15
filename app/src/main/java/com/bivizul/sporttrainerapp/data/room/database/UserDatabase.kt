package com.bivizul.sporttrainerapp.data.room.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bivizul.sporttrainerapp.data.network.dbmodels.UserDbModel
import com.bivizul.sporttrainerapp.data.room.dao.UserInfoDao

@Database(entities = [UserDbModel::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

    companion object {

        private val LOCK = Any()

        @Volatile
        private var INSTANCE: UserDatabase? = null
        private const val DB_NAME = "user_info.db"

        fun getInstance(application: Application): UserDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    UserDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}