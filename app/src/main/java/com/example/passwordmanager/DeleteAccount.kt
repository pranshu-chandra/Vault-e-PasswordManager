package com.example.passwordmanager

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

class DeleteAccount : AppCompatActivity() {
    var flag=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)
    }

    fun toInfo(view: View) {
        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()



        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()

            val countQ = db.userDao().checkExists(website, username)

            if (countQ == 0) {
                flag = 1
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            if (flag==1){
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Account doesn't exist")
                    .setPositiveButton("Try Again",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()
                        })
                    .setNegativeButton("Main Menu",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                            val intent= Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            this.finish()
                        })
                val alertDialog: AlertDialog =builder.create()
                alertDialog.show()

            }
            else{
                del()
            }

        }, 1000)

    }
    fun del(){
        val website = findViewById<EditText>(R.id.editTextWebsite).text.toString()
        val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()

            db.userDao().deleteSpecified(website,username)
        }
        Toast.makeText(this,"Account Deleted!",Toast.LENGTH_LONG).show()
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}