package com.arctouch.codechallenge.data.arch

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.arctouch.codechallenge.model.Genre

private const val DB_NAME = "arch_db"
private const val DB_VERSION = 1

@Database(entities = [Genre::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

    abstract fun genreDao() : GenreDao

}