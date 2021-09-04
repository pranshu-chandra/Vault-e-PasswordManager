package com.zylch.passwordmanager

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.zylch.passwordmanager.Data.UserDatabase
import com.scottyab.aescrypt.AESCrypt
import kotlinx.android.synthetic.main.activity_information.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.GeneralSecurityException
import java.util.*


class Information : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        var passwordInfo = findViewById<TextView>(R.id.PasswordText)
        var WebsiteNameinfo = findViewById<TextView>(R.id.WebsiteName)
        var UserNameinfo = findViewById<TextView>(R.id.User)

        val websitename = intent.getStringExtra("website")
        val usernamename= intent.getStringExtra("username")



        var messageAfterDecrypt:String="asdas"
        var flag=0


        GlobalScope.launch {
            val db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, "user_database"
            ).build()

            val countQ=db.userDao().checkExists(websitename.toString(), usernamename.toString())

            if (countQ==0){
                flag=1
                return@launch
            }

            val encryptedMsg:String=db.userDao().getPass(websitename.toString(), usernamename.toString())


           try {
                messageAfterDecrypt = AESCrypt.decrypt(usernamename, encryptedMsg).toString()
            } catch (e: GeneralSecurityException) {
                //handle error - could be due to incorrect password or tampered encryptedMsg
            }


        }
            /* Create an Intent that will start the Menu-Activity. */

        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            if (flag==1)emptymain()
            else{
                WebsiteNameinfo.text = websitename
                UserNameinfo.text = usernamename
                passwordInfo.text =  messageAfterDecrypt
            }

        }, 300)


    }

    fun Copy(view: View) {
        var passwordInfo = findViewById<TextView>(R.id.PasswordText)
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("password", passwordInfo.text.toString())
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Copied password!",Toast.LENGTH_SHORT).show()
    }

    fun GotoMain(view: View) {
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
    fun emptymain(){
        Toast.makeText(this, "Account doesn't exist",Toast.LENGTH_LONG).show()
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}