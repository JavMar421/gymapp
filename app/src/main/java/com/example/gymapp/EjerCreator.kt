package com.example.gymapp


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class EjerCreator :AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercreator)
        val database = Firebase.database("https://gym-proyect-dam-default-rtdb.europe-west1.firebasedatabase.app")
        val nameejer = findViewById<EditText>(R.id.newejer)
        val idejer = findViewById<EditText>(R.id.newejerid)
        val serieejer = findViewById<EditText>(R.id.newejerserie)
        val repesejer = findViewById<EditText>(R.id.newejerrepes)
        val pesoejer = findViewById<EditText>(R.id.newejerpeso)
        val cancelar = findViewById<Button>(R.id.Cancelar)
        val aplicar = findViewById<Button>(R.id.Aplicar)
        val logout = findViewById<ImageView>(R.id.logoutlogo)
        val edittable = findViewById<ImageView>(R.id.tablaicon)
        val editejer = findViewById<ImageView>(R.id.ejericon)
        val calendar = findViewById<ImageView>(R.id.calendaricon)
        var dato:String

        logout.setOnClickListener{
            Toast.makeText(this,"Cerrando Sesion",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        edittable.setOnClickListener{
            val intent = Intent(this, TableCreator::class.java)
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
        cancelar.setOnClickListener {
            Toast.makeText(this,"Operación Cancelada",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EjerCreator::class.java)
            startActivity(intent)
        }
        aplicar.setOnClickListener {
            database.reference.child("ejercicio").child(idejer.text.toString()).get().addOnSuccessListener {
                    dato = it.value.toString()
                    if (dato!="null"){
                        Toast.makeText(this,"Id ya en uso",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this,"Creando Ejercicio. Id:${idejer.text}",Toast.LENGTH_SHORT).show()
                        database.reference.child("ejercicio").child(idejer.text.toString()).child("nombre").setValue(nameejer.text.toString())
                        database.reference.child("ejercicio").child(idejer.text.toString()).child("peso").setValue(pesoejer.text.toString())
                        database.reference.child("ejercicio").child(idejer.text.toString()).child("reps").setValue(repesejer.text.toString())
                        database.reference.child("ejercicio").child(idejer.text.toString()).child("serie").setValue(serieejer.text.toString())
                        database.reference.child("ejercicio").child(idejer.text.toString()).child("video").setValue("https://firebasestorage.googleapis.com/v0/b/gym-proyect-dam.appspot.com/o/error.mp4?alt=media&token=7778d1cd-4a71-430f-a89f-141e504a7171")
                    }
            }


        }








    }
}