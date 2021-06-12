package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.*
import androidx.security.crypto.MasterKey


var secq=""
var seca=""
var consecq=""
var conseca =""
var count=0
class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }

    fun login(view:View){


        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val SecurityAnswer =findViewById<EditText>(R.id.SecurityA)
        val SecurityQuestion= findViewById<AutoCompleteTextView>(R.id.SecurityForgotPass)
        val masterKey = MasterKey.Builder(applicationContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        val sharedPreferences2 = create(
            applicationContext,
            "Password",
            masterKey,
            PrefKeyEncryptionScheme.AES256_SIV,
            PrefValueEncryptionScheme.AES256_GCM
        )
        secq= SecurityQuestion.text.toString()
        seca= SecurityAnswer.text.toString()

        val editor2 =sharedPreferences2.edit()
        editor2.apply{
            putString("SecurityForgotQ", secq)
            putString("SecurityForgotA",seca)
        }.apply()

         consecq = sharedPreferences2.getString("SecurityForgotQ","").toString()
       conseca = sharedPreferences2.getString("SecurityForgotA", "").toString()

        val secqog = sharedPreferences2.getString("SecurityQ","").toString()
        val  secaog = sharedPreferences2.getString("SecurityA", "").toString()

        if(secqog==consecq && secaog == conseca)
        {
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("User", "1")
            }.apply()
            val intent123 = Intent(this, MainActivity::class.java)
            startActivity(intent123)
        }

        else {
            Toast.makeText(applicationContext, "Wrong Question or Answer. Try again",Toast.LENGTH_SHORT).show()
            ++count
        }
    }
}