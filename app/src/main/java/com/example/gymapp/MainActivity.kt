package com.example.gymapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.textEmail)
        val pass = findViewById<EditText>(R.id.textPassword)
        val enviar = findViewById<Button>(R.id.botonEnviarLogin)
        val registro = findViewById<Button>(R.id.botonRegister)
        val recordar = findViewById<Switch>(R.id.switchRecordarUsuario)
        enviar.setOnClickListener() {
            if (recordar.isChecked){
            Toast.makeText(this,"Usuario Guardado",Toast.LENGTH_SHORT).show()

            }
        }
        registro.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }


}



