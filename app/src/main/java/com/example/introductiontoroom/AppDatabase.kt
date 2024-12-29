package com.example.introductiontoroom

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {

        @Volatile
        private var INSTANCE :AppDatabase? = null

        fun getDatabase(context: MainActivity): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "person_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}