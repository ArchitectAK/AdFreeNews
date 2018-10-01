package com.cogitator.adfreenews.model.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.cogitator.adfreenews.model.News

/**
 * @author Ankit Kumar on 28/09/2018
 */

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        private var INSTANCE: NewsDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): NewsDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NewsDatabase::class.java, "News.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}