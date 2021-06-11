package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.scottyab.aescrypt.AESCrypt
import java.security.GeneralSecurityException
import kotlin.random.Random


class Generate : AppCompatActivity() {

    val numberSet = "0123456789"
    val upperSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val lowerSet="abcdefghijklmnopqrstuvwxyz"
    val specialSet="!@#$%^&*()-_=+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate)

        val website=findViewById<EditText>(R.id.editTextWebsite).toString()
        val username=findViewById<EditText>(R.id.editTextWebsite).toString()

        val random = Random(System.nanoTime())
        val password = StringBuilder()

        for (i in 0 until 4)
        {
            val rIndex = random.nextInt(upperSet.length)
            password.append(upperSet[rIndex])
        }
        for (i in 0 until 4)
        {
            val rIndex = random.nextInt(lowerSet.length)
            password.append(lowerSet[rIndex])
        }
        for (i in 0 until 4)
        {
            val rIndex = random.nextInt(specialSet.length)
            password.append(specialSet[rIndex])
        }
        for (i in 0 until 4)
        {
            val rIndex = random.nextInt(numberSet.length)
            password.append(numberSet[rIndex])
        }
        val passCharArray=password.toString().toCharArray()
        passCharArray.shuffle()
        val generatedPass = passCharArray.concatToString()

        Toast.makeText(this, generatedPass, Toast.LENGTH_SHORT).show()
        Log.i("Password",generatedPass)

        lateinit var encryptedMsg:String
        lateinit var decryptedMsg:String

        try {
            encryptedMsg = AESCrypt.encrypt(username, generatedPass)
            Toast.makeText(this,encryptedMsg,Toast.LENGTH_SHORT).show()
        } catch (e: GeneralSecurityException) {
            //handle error
            Toast.makeText(this,"Some Error occured",Toast.LENGTH_SHORT).show()
        }

        try {
            decryptedMsg = AESCrypt.decrypt(username, encryptedMsg)
            Toast.makeText(this,decryptedMsg,Toast.LENGTH_SHORT).show()
        } catch (e: GeneralSecurityException) {
            //handle error
            Toast.makeText(this,"Some Error occured",Toast.LENGTH_SHORT).show()
        }



    }


    fun toInfo(view: View) {
        val intent= Intent(this,Information::class.java)
        startActivity(intent)
    }
}