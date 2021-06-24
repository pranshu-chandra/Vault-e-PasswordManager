package com.example.passwordmanager.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insert(user:User)

    @Delete
    fun delete(user:User)

    @Query("SELECT * FROM user WHERE website = :website AND username = :username")
    fun getPass(website: String, username: String): User

}