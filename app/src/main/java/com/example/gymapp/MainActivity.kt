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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//public var savemail = ""
//public var savepass = ""
var saveuser = ""
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    var auth: FirebaseAuth = Firebase.auth
    @SuppressLint("SetTextI18n", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoGrande = findViewById<ImageView>(R.id.logoGrande)
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
        enviar.setOnClickListener{
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
                            saveuser= email.text.toString().substringBefore("@")
                            Log.d(TAG, "signInWithEmail:success")

                            if (recordar.isChecked){
                                //savemail=email.text.toString()
                                //savepass=pass.text.toString()

                                Toast.makeText(this,"$saveuser guardado",Toast.LENGTH_SHORT).show()
                            }

                            var intent = Intent(this, TableActivityV2::class.java)
                            if (email.text.contains("admin")){intent= Intent(this, EjerCreator::class.java)}
                            startActivity(intent)
                        } else {
                            error.text=error.text.toString()+" Usuario o Contraseña Incorrectos "
                            error.visibility= View.VISIBLE
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

}



