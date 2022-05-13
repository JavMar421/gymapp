package com.example.gymapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.gymapp.databinding.ActivityRegisterBinding
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import kotlin.concurrent.timer

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = findViewById<EditText>(R.id.textEmail)
        val pass = findViewById<EditText>(R.id.textPassword)
        val passverify = findViewById<EditText>(R.id.repeatPassword)
        val error = findViewById<TextView>(R.id.textoErrorLogin2)
        val registro = findViewById<Button>(R.id.botonRegister)

        registro.setOnClickListener() {
            if (passverify.text.contentEquals(pass.text) && email.text.contains("@")){
                Toast.makeText(this,"Todo Correcto, Creando Usuario",Toast.LENGTH_SHORT).show()
            }


        }

    }



}

