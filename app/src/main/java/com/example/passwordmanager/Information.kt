package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.room.Room
import com.example.passwordmanager.Data.User
import com.example.passwordmanager.Data.UserDatabase
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class Information : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)




        var websitename = intent.getStringExtra("website")
        var usernamename= intent.getStringExtra("username")

        var WebsiteNameinfo = findViewById<TextView>(R.id.WebsiteName)
        var UserNameinfo = findViewById<TextView>(R.id.User)
        var Passwordinfo = findViewById<TextView>(R.id.PasswordText)



        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()
          Passwordinfo.text =  db.userDao().getPass(websitename.toString(), usernamename.toString())
        }

        WebsiteNameinfo.text =websitename
        UserNameinfo.text=usernamename

    }

    fun Copy(view: View) {

    }
    fun GotoMain(view: View) {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}