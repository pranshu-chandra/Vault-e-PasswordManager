package com.example.passwordmanager

import android.app.Application
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.passwordmanager.R.id.TextPassword
import java.util.concurrent.Executor


class LoginActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
 //   private val km = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    goToMainActivity()

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })


            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
               // .setNegativeButtonText("Use account password")
                .setAllowedAuthenticators(BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                .build()



    }


    fun toMainActivity(view: View) {

        val password = findViewById<EditText>(TextPassword)
        val pass= password.text.toString()
        val sharedPreferences2 = getSharedPreferences("Password", MODE_PRIVATE)
        val sharedPreferences = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val checking = sharedPreferences2.getString("Password", "")
        if(checking.equals(pass))
        {
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("User", "1")
            }.apply()


            biometricPrompt.authenticate(promptInfo)}
        else
        {
            Toast.makeText(applicationContext,"Wrong Password",Toast.LENGTH_SHORT).show()
        }


    }

    fun goToMainActivity(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }


}