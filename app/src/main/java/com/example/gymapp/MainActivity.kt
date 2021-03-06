package com.example.gymapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.UserApplication.Companion.datos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



var saveuser = ""
var adminmode=false
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    var auth: FirebaseAuth = Firebase.auth
    @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adminmode=false
        saveuser=""
        val logoGrande = findViewById<ImageView>(R.id.logoGrande)
        val email = findViewById<EditText>(R.id.textEmail)
        val pass = findViewById<EditText>(R.id.textPassword)
        val enviar = findViewById<Button>(R.id.botonEnviarLogin)
        val registro = findViewById<Button>(R.id.botonRegister)
        val recordar = findViewById<Switch>(R.id.switchRecordarUsuario)
        val error = findViewById<TextView>(R.id.textoErrorLogin)
        val database = Firebase.database("https://gym-proyect-dam-default-rtdb.europe-west1.firebasedatabase.app")

        email.setText(datos.getName())
        pass.setText(datos.getPass())

        logoGrande.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            if (email.text.toString() == datos.getName() && pass.text.toString() == datos.getPass()){
                enviar.callOnClick()
                error.visibility=View.INVISIBLE
            }
            delay(1000)
            logoGrande.visibility = View.GONE
        }



        enviar.setOnClickListener{
            error.text = "Error: "
            error.visibility = View.INVISIBLE

                if (email.text.toString().isEmpty() || pass.text.toString().isEmpty()) {
                    error.text = error.text.toString() + "Elemento Vac??o "
                    error.visibility = View.VISIBLE
                } else {
                    auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                saveuser = email.text.toString().substringBefore("@")
                                Log.d(TAG, "signInWithEmail:success")

                                if (recordar.isChecked) {
                                    guardar(email.text.toString(), pass.text.toString())

                                    Toast.makeText(this, "$saveuser guardado", Toast.LENGTH_SHORT)
                                        .show()
                                }

                                var intent = Intent(this, TableActivityV2::class.java)

                                if (email.text.contains("admin")) {

                                    adminmode = true
                                    intent = Intent(this, TableCreatorV2::class.java)
                                }
                                else{
                                    database.reference.child("usuarios").child(saveuser).child("0").get().addOnSuccessListener {
                                        if (it.value==null){
                                            database.reference.child("usuarios").child(saveuser).setValue("")
                                        }
                                    }
                                }
                                startActivity(intent)
                            } else {
                                error.text =
                                    error.text.toString() + " Usuario o Contrase??a Incorrectos "
                                error.visibility = View.VISIBLE
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.exception)

                            }
                        }
                }
            }
        registro.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }
    private fun guardar(user:String, pass:String){
        datos.saveName(user)
        datos.savePass(pass)
    }


}



