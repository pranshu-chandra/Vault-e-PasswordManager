package com.example.passwordmanager.Data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.passwordmanager.Data.User as User

@Dao
interface UserDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insert(user: User)

        @Query("DELETE from user")
        fun delete()


    @Query("SELECT password FROM user WHERE website LIKE :website AND username LIKE  :username")
     fun getPass(website: String, username: String): String

}