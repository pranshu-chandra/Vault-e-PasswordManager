package com.zylch.passwordmanager.Data

import android.content.Context
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {


    var loginDatabase: UserDatabase? = null
    var readAllData: LiveData<User>? = null
    fun initializeDB(context: Context) : UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    fun insert (user: User){
        userDao.insert(user)
    }

    fun delete() {
        userDao.delete()
    }

    fun getPass(website: String, username: String):String{
        return userDao.getPass(website,username)
     }


}