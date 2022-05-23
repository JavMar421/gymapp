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
//import com.example.gymapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

public var cosa = ""
public var cosa2 = ""
class MainActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding
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
            error.text="Error: "
            error.visibility= View.INVISIBLE
            if (email.text.toString().isEmpty() || pass.text.toString().isEmpty()){
                error.text=error.text.toString()+"Elemento Vacío "
                error.visibility= View.VISIBLE
            }
            else{
                auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            if (recordar.isChecked){
                                Toast.makeText(this,"Usuario Guardado",Toast.LENGTH_SHORT).show()
                                
                                cosa=email.text.toString()
                                cosa2=pass.text.toString()
                            }
                            val intent = Intent(this, TableActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            updateUI(null)
                        }
                    }
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



