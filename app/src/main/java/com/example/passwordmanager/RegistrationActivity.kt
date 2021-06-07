package com.example.passwordmanager

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

var pass=""
var confirm=""
class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var password = findViewById<EditText>(R.id.editTextTextPassword)
        var passwordConfirm = findViewById<EditText>(R.id.editTextTextPassword2)
        var securityAnswer = findViewById<EditText>(R.id.SecurityQA)




    }

      @SuppressLint("ShowToast")
      fun toLogin(view:View) {
          val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
          var sharedPreferences2 = getSharedPreferences("Password", MODE_PRIVATE)
          val password = findViewById<EditText>(R.id.editTextTextPassword)
          val passwordConfirm = findViewById<EditText>(R.id.editTextTextPassword2)
        pass=password.text.toString()
          confirm=passwordConfirm.text.toString()

          if(pass== confirm) {
              val editor = sharedPreferences.edit()
              editor.apply {
                  putString("User", "1")
              }.apply()

              val editor2 =sharedPreferences2.edit()
              editor2.apply{
                  putString("Password", pass)
              }.apply()
              Toast.makeText(
                  applicationContext,
                  "Welcome",
                  Toast.LENGTH_SHORT
              ).show()
              val intent123 = Intent(this, LoginActivity::class.java)
              startActivity(intent123)

          }
          else {
              Toast.makeText(
                  applicationContext,
                  "Please Enter Correct Password",
                  Toast.LENGTH_SHORT
              ).show()
          }
    }
}