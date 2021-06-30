package com.example.passwordmanager

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.passwordmanager.Data.User
import com.example.passwordmanager.Data.UserDatabase
import com.scottyab.aescrypt.AESCrypt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.GeneralSecurityException
import java.util.*
import kotlin.random.Random

class Generate : AppCompatActivity() {
    var flag=0
    val numberSet = "0123456789"
    val upperSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val lowerSet="abcdefghijklmnopqrstuvwxyz"
    val specialSet="!@#$%^&*()-_=+"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate)
    }

    fun toInfo(view: View) {
        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
            .lowercase(Locale.getDefault())
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        if (website.isEmpty()){
            Toast.makeText(this, "Please fill website name",Toast.LENGTH_SHORT).show()
            return
        }
        else if (username.isEmpty()){
            Toast.makeText(this, "Please fill username",Toast.LENGTH_SHORT).show()
            return
        }

        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()

            val countQ = db.userDao().checkExists(website, username)

            if (countQ != 0) {
                flag = 1
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            if (flag==1){
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Account already exists")
                    .setPositiveButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            val intent= Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        })
                    .setNegativeButton("Replace",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                            gen()
                        })
                val alertDialog: AlertDialog=builder.create()
                alertDialog.show()

            }
            else{
                gen()
            }

        }, 1000)




    }
    fun gen(){

        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()

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

        // Toast.makeText(this, generatedPass, Toast.LENGTH_SHORT).show()
        Log.i("Password",generatedPass)

        lateinit var encryptedMsg:String



        GlobalScope.launch {
            try {
                encryptedMsg = AESCrypt.encrypt(username, generatedPass)
                //  Toast.makeText(this,encryptedMsg,Toast.LENGTH_SHORT).show()
            } catch (e: GeneralSecurityException) {
                //handle error
                // Toast.makeText(this,"Some Error occured",Toast.LENGTH_SHORT).show()
            }

            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()
            if (flag==1){
                db.userDao().update(website,username,encryptedMsg)
            }
            else{
                db.userDao().insert(User(0,website,username,encryptedMsg))
            }

        }



        Toast.makeText(this, "Account Added",Toast.LENGTH_LONG).show()


        val intent= Intent(this,Information::class.java)
        intent.putExtra("website", website)
        intent.putExtra("username", username)
        startActivity(intent)
    }
}
