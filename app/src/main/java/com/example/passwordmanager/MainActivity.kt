package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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
}