package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


      setContentView(R.layout.splashscreen)
        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val checking = sharedPreferences.getString("User", "")

        Handler().postDelayed({
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
        },1000)


    }
}