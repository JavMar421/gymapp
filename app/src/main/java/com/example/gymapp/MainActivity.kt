package com.example.gymapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    var auth: FirebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoGrande = findViewById<ImageView>(R.id.logoGrande)
        val logo = findViewById<ImageView>(R.id.logo)
        val email = findViewById<EditText>(R.id.textEmail)
        val pass = findViewById<EditText>(R.id.textPassword)
        val enviar = findViewById<Button>(R.id.botonEnviarLogin)
        val registro = findViewById<Button>(R.id.botonRegister)
        val recordar = findViewById<Switch>(R.id.switchRecordarUsuario)
        val error = findViewById<TextView>(R.id.textoErrorLogin)

        logoGrande.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            logoGrande.visibility = View.GONE

        }


        enviar.setOnClickListener() {
            error.visibility= View.INVISIBLE
            auth.signInWithEmailAndPassword(email.text.toString(),pass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(baseContext, "Authentication succes.",Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        error.visibility= View.VISIBLE
                        Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }

            if (recordar.isChecked){
                Toast.makeText(this,"Usuario Guardado",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TableActivity::class.java)
                startActivity(intent)
            }


        }
        logo.setOnClickListener{val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
        registro.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            //reload();
        }

    }
    fun updateUI(user: FirebaseUser?) {

    }


}



