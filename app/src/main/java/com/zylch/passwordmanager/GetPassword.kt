package com.zylch.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.zylch.passwordmanager.Data.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class GetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_password)



    }

    fun toInfo(view: View) {

        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
            .lowercase(Locale.getDefault())
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        if (website.isEmpty()){
            Toast.makeText(this, "Please fill website name",Toast.LENGTH_SHORT).show()
            return
        }
        else if (username.isEmpty()){
            Toast.makeText(this, "Please fill username",Toast.LENGTH_SHORT).show()
            return
        }
        var flag=0
        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()
            if (db.userDao().checkExists(website,username)==0){
                flag=1
                return@launch
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            if (flag==1)emptymain()
            else{
                val intent= Intent(this, Information::class.java)
                intent.putExtra("username", username)
                intent.putExtra("website", website)
                startActivity(intent)
            }

        }, 300)




    }
    fun emptymain(){
        Toast.makeText(this, "Account doesn't exist", Toast.LENGTH_LONG).show()
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}