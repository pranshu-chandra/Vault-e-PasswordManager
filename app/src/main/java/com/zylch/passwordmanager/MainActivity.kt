package com.zylch.passwordmanager

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.*
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.zylch.passwordmanager.Data.UserDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun GetPassword(view: View) {
        val intent= Intent(this, GetPassword::class.java)
        startActivity(intent)
    }
    fun Generate(view: View) {
        val intent= Intent(this, Generate::class.java)
        startActivity(intent)
    }
    fun Existing(view: View) {
        val intent= Intent(this, Existing::class.java)
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
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
    @DelicateCoroutinesApi
    fun Delete(view: View){
        val masterKey = MasterKey.Builder(applicationContext)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val sharedPreferences2 = EncryptedSharedPreferences.create(applicationContext,"Password",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        val builder2 = Builder(this)
        builder2.setMessage("Are you sure you want to delete your account?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
        GlobalScope.launch {
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("User", "0")
            }.apply()

            val editor2 = sharedPreferences2.edit()
            editor2.clear().apply()


            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()
            db.userDao().delete()

        }
                    dialog.dismiss()
                    Toast.makeText(this,"Account Deleted!",Toast.LENGTH_SHORT).show()
                    val intent3 = Intent(this, RegistrationActivity::class.java)
                    intent3.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent3)
                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    dialog.dismiss()

                })
        val alertDialog: AlertDialog =builder2.create()
        alertDialog.show()


    }

    fun DeleteAccount(view: View) {
        val intent= Intent(this, DeleteAccount::class.java)
        startActivity(intent)
    }

    fun DeleteAll(view: View) {
        val builder = Builder(this)
        builder.setMessage("Are you sure you want to delete all account data?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    GlobalScope.launch {

                        val db = Room.databaseBuilder(
                            applicationContext,
                            UserDatabase::class.java, "user_database"
                        ).build()
                            db.userDao().delete()
                        }
                    Toast.makeText(this,"All data deleted!",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                    dialog.dismiss()
                })
        val alertDialog: AlertDialog =builder.create()
        alertDialog.show()
    }
}