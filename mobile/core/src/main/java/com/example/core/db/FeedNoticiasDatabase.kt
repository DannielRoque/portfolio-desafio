package com.example.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [PortalViewData::class], version = 1, exportSchema = false)
abstract class FeedNoticiasDatabase : RoomDatabase() {

//    abstract fun PortalDao(): PortalDao

    companion object {
        @Volatile
        private var INSTANCE: FeedNoticiasDatabase? = null

        fun getDatabase(context: Context): FeedNoticiasDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeedNoticiasDatabase::class.java,
                    "feed_noticias_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}