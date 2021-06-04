package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Information : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
    }

    fun Copy(view: View) {
        
    }
    fun GotoMain(view: View) {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}