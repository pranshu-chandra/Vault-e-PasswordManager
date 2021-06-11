package com.example.passwordmanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(AccountData::class), version = 1, exportSchema = false)

abstract class AccountsDatabase : RoomDatabase(){

    abstract fun AccountsDataDao():AccountsDataDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AccountsDatabase? = null

        fun getDatabase(context: Context): AccountsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountsDatabase::class.java,
                    "account_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}