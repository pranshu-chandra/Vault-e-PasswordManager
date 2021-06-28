package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.room.Room
import com.example.passwordmanager.Data.User
import com.example.passwordmanager.Data.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_password)



    }

    fun toInfo(view: View) {

        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()


        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()
            db.userDao().getPass(website,username)
        }



        val intent= Intent(this,Information::class.java)
        intent.putExtra("username", username)
        intent.putExtra("website", website)
        startActivity(intent)
    }
}