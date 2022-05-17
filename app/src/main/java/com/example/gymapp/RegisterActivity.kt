package com.example.gymapp

import android.accounts.AbstractAccountAuthenticator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.Visibility
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.gymapp.databinding.ActivityRegisterBinding
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import java.net.PasswordAuthentication
import kotlin.concurrent.timer

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.textEmail)
        val pass = findViewById<EditText>(R.id.textPassword)
        val pass2 = findViewById<EditText>(R.id.repeatPassword)
        val error = findViewById<TextView>(R.id.textoErrorLogin2)
        val registro = findViewById<Button>(R.id.botonRegister)
        val inicio = findViewById<Button>(R.id.botonLogin)
        registro.setOnClickListener() {
            val intent = Intent(this, TableActivity::class.java)
            startActivity(intent)
        }
        inicio.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}

