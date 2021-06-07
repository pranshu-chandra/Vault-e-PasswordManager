package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText


class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var password = findViewById<EditText>(R.id.editTextTextPassword)
        var passwordConfirm = findViewById<EditText>(R.id.editTextTextPassword2)
        var securityAnswer = findViewById<EditText>(R.id.SecurityQA)




    }

      fun toLogin(view:View) {
          val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)


        val editor=sharedPreferences.edit()
        editor.apply {
            putString("User", "1")
        }.apply()

          val intent123 = Intent(this, LoginActivity::class.java)
          startActivity(intent123)

    }
}