package com.example.rxjava.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Repo::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRepoDAO(): RepoDAO

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE?.let { INSTANCE } ?: run {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "repo.db")
                    .build()
            }
            return INSTANCE
        }
    }
}