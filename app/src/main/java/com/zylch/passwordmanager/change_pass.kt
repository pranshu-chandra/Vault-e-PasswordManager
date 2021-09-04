package com.zylch.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


class change_pass : AppCompatActivity() {


    var pass=""
    var confirm=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)
    }


    fun ChangePass(view: View){

        var password = findViewById<EditText>(R.id.changenewpass)
        var passwordConfirm = findViewById<EditText>(R.id.confirmchangepass)
        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val masterKey = MasterKey.Builder(applicationContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        val sharedPreferences2 = EncryptedSharedPreferences.create(
            applicationContext,
            "Password",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        pass=password.text.toString()
        confirm=passwordConfirm.text.toString()


        if(pass== confirm && pass != "") {
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("User", "1")
            }.apply()

            val editor2 =sharedPreferences2.edit()
            editor2.apply{
                remove("Password")
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