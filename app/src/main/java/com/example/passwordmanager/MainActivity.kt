package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun GetPassword(view: View) {
        val intent= Intent(this,GetPassword::class.java)
        startActivity(intent)
    }
    fun Generate(view: View) {
        val intent= Intent(this,Generate::class.java)
        startActivity(intent)
    }
    fun Existing(view: View) {
        val intent= Intent(this,Existing::class.java)
        startActivity(intent)
    }
    fun Log(view: View)
    {
        val sharedPreferences =getSharedPreferences("sharedPref", MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putString("User","2")
        }.apply()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    fun Delete(view: View){
        val masterKey = MasterKey.Builder(applicationContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val sharedPreferences2 = EncryptedSharedPreferences.create(applicationContext,"Password",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("User", "0")
        }.apply()

        val editor2 =sharedPreferences2.edit()
        editor2.clear().apply()

        val intent3 = Intent(this, RegistrationActivity::class.java)
        startActivity(intent3)

    }
}