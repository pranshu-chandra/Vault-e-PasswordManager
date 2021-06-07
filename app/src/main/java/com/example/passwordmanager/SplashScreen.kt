package com.example.passwordmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


      setContentView(R.layout.splashscreen)
        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val checking = sharedPreferences.getString("User", "")

        Handler().postDelayed({},5000)
        when {
            checking.equals("1") -> {
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
            }
            checking.equals("2") -> {
                val intent2 = Intent(this, LoginActivity::class.java)
                startActivity(intent2)
            }
            else -> {
                val intent3 = Intent(this, RegistrationActivity::class.java)
                startActivity(intent3)

            }
        }


    }
}