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

        Handler().postDelayed({},5000)
        when {
           
            checking.equals("2") -> {
                val intent2 = Intent(this, LoginActivity::class.java)
                startActivity(intent2)
                this.finish()

            }
            else -> {
                val intent3 = Intent(this, RegistrationActivity::class.java)
                startActivity(intent3)
                this.finish()


            }
        }


    }


}