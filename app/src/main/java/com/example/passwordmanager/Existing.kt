package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.passwordmanager.Data.User
import com.example.passwordmanager.Data.UserDao
import com.example.passwordmanager.Data.UserDatabase
import com.example.passwordmanager.Data.UserViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Handler

class Existing : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel




    val website="Facebook"
    val username ="Naman"
    val password = "LOLOL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing)

       // val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
       // val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
       // val password = findViewById<EditText>(R.id.editTextPassword).text.toString()


        var pass:String?=""

        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()
            db.userDao().insert(User(0,website,username,password))
        }

        Toast.makeText(this, "Account Added",Toast.LENGTH_LONG).show()







    }


    fun toInfo(view: View) {



        // mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        // val intent= Intent(this,Information::class.java)
       // startActivity(intent)
    }
}