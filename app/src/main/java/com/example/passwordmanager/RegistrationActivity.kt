package com.example.passwordmanager

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.*
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import androidx.security.crypto.MasterKeys.*
import com.google.android.material.textfield.TextInputLayout
import java.security.Provider

var pass=""
var confirm=""
class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var password = findViewById<EditText>(R.id.editTextTextPassword)
        var passwordConfirm = findViewById<EditText>(R.id.editTextTextPassword2)
        var securityAnswer = findViewById<EditText>(R.id.SecurityQA)
                val spinner= findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

            val secq= resources.getStringArray(R.array.SecurityQuestions)
        val arrayAdapter=ArrayAdapter(applicationContext,R.layout.dropdown_item,secq)
              spinner.setAdapter(arrayAdapter)

    }



    @SuppressLint("ShowToast")
      fun toLogin(view:View) {
          val masterKey = MasterKey.Builder(applicationContext)
              .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
              .build()
          val sharedPreferences2 = create(applicationContext,
              "Password",
              masterKey,
              PrefKeyEncryptionScheme.AES256_SIV,
              PrefValueEncryptionScheme.AES256_GCM)


          val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)

          val password = findViewById<EditText>(R.id.editTextTextPassword)
          val passwordConfirm = findViewById<EditText>(R.id.editTextTextPassword2)
        pass=password.text.toString()
          confirm=passwordConfirm.text.toString()

          if(pass== confirm && pass != "") {
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
                  "Please Enter Correct Password and don't leave the field empty",
                  Toast.LENGTH_SHORT
              ).show()
          }
    }
}