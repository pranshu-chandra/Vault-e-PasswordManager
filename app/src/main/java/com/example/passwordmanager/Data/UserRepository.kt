package com.example.passwordmanager.Data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao:UserDao) {

    fun insert (user:User){
        userDao.insert(user)
    }

    fun delete(user:User){
        userDao.delete(user)
    }

    fun getPass(website: String, username: String):User{
        return userDao.getPass(website,username)
    }

}