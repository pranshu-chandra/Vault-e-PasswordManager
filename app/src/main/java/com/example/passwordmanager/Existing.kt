package com.example.passwordmanager

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.passwordmanager.Data.User
import com.example.passwordmanager.Data.UserDao
import com.example.passwordmanager.Data.UserDatabase
import com.example.passwordmanager.Data.UserViewModel
import com.scottyab.aescrypt.AESCrypt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.GeneralSecurityException
import java.util.logging.Handler

class Existing : AppCompatActivity() {
    var flag=0

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_existing)
    }


    fun toInfo(view: View) {

        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val password  = findViewById<EditText>(R.id.editTextPassword).text.toString()

        // mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


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

        android.os.Handler(Looper.getMainLooper()).postDelayed({
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
                            save()
                        })
                val alertDialog: AlertDialog =builder.create()
                alertDialog.show()

            }
            else{
                save()
            }

        }, 1000)



    }

    fun save(){
        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        val password  = findViewById<EditText>(R.id.editTextPassword).text.toString()

        var encryptedMsg:String=""


        try {
            encryptedMsg = AESCrypt.encrypt(username, password).toString()
            //  Toast.makeText(this,encryptedMsg,Toast.LENGTH_SHORT).show()
        } catch (e: GeneralSecurityException) {
            //handle error
            Toast.makeText(this,"Some Error occured",Toast.LENGTH_SHORT).show()
        }

        GlobalScope.launch {
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

        val intent= Intent(this,MainActivity::class.java)

        startActivity(intent)
        this.finish()
    }
}