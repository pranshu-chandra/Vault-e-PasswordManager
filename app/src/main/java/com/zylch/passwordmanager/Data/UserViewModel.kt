package com.zylch.passwordmanager.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

  //  private val getPass: LiveData<User>
    private val repository: UserRepository
    lateinit var getPass: String


    init{
        val userDao= UserDatabase.getDatabase(application).userDao()
        repository= UserRepository(userDao)
    }

    fun getPass(website: String, username: String) {
            viewModelScope.launch (Dispatchers.IO){
                getPass=repository.getPass(website,username)
            }
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete()
        }
    }


    fun insert(user: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(user)
        }
    }

}