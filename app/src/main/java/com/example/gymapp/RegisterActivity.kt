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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    var auth: FirebaseAuth = Firebase.auth
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
        registro.setOnClickListener {
            error.visibility=View.INVISIBLE
            error.text="Error: "

            var verify=0

            if (email.text.contains("@") && email.text.contains(".")){verify++}
            else {
                error.text=error.text.toString()+"Dirección Incorrecta "
                error.visibility = View.VISIBLE}

            if (pass.text.toString().length>=6)
            {verify++}
            else {
                error.text=error.text.toString()+"Contraseñas muy Corta "
                error.visibility = View.VISIBLE}

            if (pass.text.toString()==pass2.text.toString())
            {verify++}
            else {
                error.text=error.text.toString()+"Contraseñas no Iguales "
                error.visibility = View.VISIBLE}
            if (!email.text.contains("admin")){verify++}
            else {
                error.text=error.text.toString()+"Dirección Reservada a Administradores "
                error.visibility = View.VISIBLE}
            if (verify==4){
            auth.createUserWithEmailAndPassword(email.text.toString(), pass.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        saveuser= email.text.toString().substringBefore("@")
                        Toast.makeText(baseContext, "Usuario $saveuser creado", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        error.visibility = View.VISIBLE
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        error.text= error.text.toString()+"Email ya en uso "
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
            }
        }

        inicio.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun updateUI(user: FirebaseUser?) {
    }


}

