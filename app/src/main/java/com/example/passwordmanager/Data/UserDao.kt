package com.example.passwordmanager.Data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.passwordmanager.Data.User as User

@Dao
interface UserDao {
    @Insert(onConflict= REPLACE)
    fun insert(user: User)

    @Query("DELETE from user")
        fun delete()


    @Query("SELECT password FROM user WHERE website LIKE :website AND username LIKE  :username")
     fun getPass(website: String, username: String): String

     @Query ("SELECT COUNT(*) FROM user WHERE website LIKE :website AND username LIKE  :username")
     fun checkExists(website: String,username: String):Int

     @Query("UPDATE user SET password = :pass WHERE website LIKE :website AND username LIKE :username")
     fun update(website: String,username: String, pass:String)

    @Query("DELETE from user WHERE website LIKE :website AND username LIKE :username")
    fun deleteSpecified(website: String,username: String)

}