package com.example.gymapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TableCreatorV2 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tablacreatorv2)
        val database = Firebase.database("https://gym-proyect-dam-default-rtdb.europe-west1.firebasedatabase.app")
        val logout = findViewById<ImageView>(R.id.logoutlogo)
        val edittable = findViewById<ImageView>(R.id.tablaicon)
        val editejer = findViewById<ImageView>(R.id.ejericon)
        val calendar = findViewById<ImageView>(R.id.calendaricon)

        val usuario = findViewById<EditText>(R.id.textusuario)
        val ejercicio = findViewById<EditText>(R.id.editej)
        val series = findViewById<EditText>(R.id.ejerserie)
        val repes = findViewById<EditText>(R.id.ejerrepes)
        val peso = findViewById<EditText>(R.id.ejerpeso)
        val cancelar = findViewById<Button>(R.id.Cancelar)
        val add = findViewById<Button>(R.id.add)
        val visualizar = findViewById<Button>(R.id.visualizar)
        val borrar = findViewById<Button>(R.id.borrarTabla)
        //Menu
        logout.setOnClickListener{
            Toast.makeText(this,"$saveuser ha Cerrado la Sesión", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        edittable.setOnClickListener{
            val intent = Intent(this, TableCreatorV2::class.java)
            startActivity(intent)
        }
        editejer.setOnClickListener{
            val intent = Intent(this, EjerCreator::class.java)
            startActivity(intent)
        }
        calendar.setOnClickListener{
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        var num=0
        add.setOnClickListener {
            if (ejercicio.text.isNotEmpty() && peso.text.isNotEmpty() && repes.text.isNotEmpty() && series.text.isNotEmpty()) {
                database.reference.child("usuarios").child(usuario.text.toString()).child("$num")
                    .get().addOnSuccessListener {
                        if (it.value == null) {
                            database.reference.child("usuarios").child(usuario.text.toString())
                                .child("$num").child("nombre").setValue(ejercicio.text.toString())
                            database.reference.child("usuarios").child(usuario.text.toString())
                                .child("$num").child("peso").setValue(peso.text.toString())
                            database.reference.child("usuarios").child(usuario.text.toString())
                                .child("$num").child("reps").setValue(repes.text.toString())
                            database.reference.child("usuarios").child(usuario.text.toString())
                                .child("$num").child("serie").setValue(series.text.toString())
                            database.reference.child("usuarios").child(usuario.text.toString())
                                .child("$num").child("video")
                                .setValue("https://firebasestorage.googleapis.com/v0/b/gym-proyect-dam.appspot.com/o/error.mp4?alt=media&token=7778d1cd-4a71-430f-a89f-141e504a7171")

                            Toast.makeText(this, "Ejercicio Añadido", Toast.LENGTH_SHORT).show()
                            ejercicio.text.clear()
                            peso.text.clear()
                            repes.text.clear()
                            series.text.clear()
                        } else {
                            num++
                            add.callOnClick()
                        }
                    }

            }
            else{Toast.makeText(this, "Rellena los Campos Vacíos", Toast.LENGTH_SHORT).show() }
        }
        cancelar.setOnClickListener {
            val intent = Intent(this, TableCreatorV2::class.java)
            startActivity(intent)
        }
        borrar.setOnClickListener {
            database.reference.child("usuarios").child(usuario.text.toString()).removeValue()
            Toast.makeText(this, "Tabla de Usuario Borrada", Toast.LENGTH_SHORT).show()
            num=0
        }

        visualizar.setOnClickListener {
            saveuser=usuario.text.toString()
            val intent = Intent(this, TableActivityV2::class.java)
            startActivity(intent)
        }

    }
}