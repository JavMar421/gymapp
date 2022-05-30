package com.example.gymapp
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TableCreator :AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablecreator)
        val database = Firebase.database("https://gym-proyect-dam-default-rtdb.europe-west1.firebasedatabase.app")
        val cancelar = findViewById<Button>(R.id.Cancelar)
        val aplicar = findViewById<Button>(R.id.Aplicar)
        val usuario = findViewById<EditText>(R.id.textusuario)
        val logout = findViewById<ImageView>(R.id.logoutlogo)
        val edittable = findViewById<ImageView>(R.id.tablaicon)
        val editejer = findViewById<ImageView>(R.id.ejericon)
        val calendar = findViewById<ImageView>(R.id.calendaricon)
        var num=0
        val ch1 = findViewById<CheckBox>(R.id.checkBox1)
        val ch2 = findViewById<CheckBox>(R.id.checkBox2)
        val ch3 = findViewById<CheckBox>(R.id.checkBox3)
        val ch4 = findViewById<CheckBox>(R.id.checkBox4)
        val ch5 = findViewById<CheckBox>(R.id.checkBox5)
        val ch6 = findViewById<CheckBox>(R.id.checkBox6)
        val ch7 = findViewById<CheckBox>(R.id.checkBox7)
        val ch8 = findViewById<CheckBox>(R.id.checkBox8)
        val ch9 = findViewById<CheckBox>(R.id.checkBox9)
        val ch0 = findViewById<CheckBox>(R.id.checkBox0)

        val c1 = findViewById<Button>(R.id.ejer1)
        val c2 = findViewById<Button>(R.id.ejer2)
        val c3 = findViewById<Button>(R.id.ejer3)
        val c4 = findViewById<Button>(R.id.ejer4)
        val c5 = findViewById<Button>(R.id.ejer5)
        val c6 = findViewById<Button>(R.id.ejer6)
        val c7 = findViewById<Button>(R.id.ejer7)
        val c8 = findViewById<Button>(R.id.ejer8)
        val c9 = findViewById<Button>(R.id.ejer9)
        val c0 = findViewById<Button>(R.id.ejer0)
        val lista: MutableList<String> = mutableListOf()
        logout.setOnClickListener{
            Toast.makeText(this,"$saveuser ha Cerrado la Sesión",Toast.LENGTH_SHORT).show()
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
        c1.setOnClickListener {
            ch1.isChecked = ch1.isChecked != true
        }
        c2.setOnClickListener {
            ch2.isChecked = ch2.isChecked != true


        }
        c3.setOnClickListener {
            ch3.isChecked = ch3.isChecked != true


        }
        c4.setOnClickListener {
            ch4.isChecked = ch4.isChecked != true


        }
        c5.setOnClickListener {
            ch5.isChecked = ch5.isChecked != true


        }
        c6.setOnClickListener {
            ch6.isChecked = ch6.isChecked != true


        }
        c7.setOnClickListener {
            ch7.isChecked = ch7.isChecked != true


        }
        c8.setOnClickListener {
            ch8.isChecked = ch8.isChecked != true


        }
        c9.setOnClickListener {
            ch9.isChecked = ch9.isChecked != true


        }
        c0.setOnClickListener {
            ch0.isChecked = ch0.isChecked != true
        }


        cancelar.setOnClickListener {
            Toast.makeText(this,"Operación Cancelada",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, TableCreator::class.java)
            startActivity(intent)
        }

        aplicar.setOnClickListener {
            if (usuario.text.toString().isEmpty()) {
                Toast.makeText(this, "Seleccione un Usuario", Toast.LENGTH_SHORT).show()
            } else {
                if (ch1.isChecked) {
                    lista.add("1")
                }
                if (ch2.isChecked) {
                    lista.add("2")
                }
                if (ch3.isChecked) {
                    lista.add("3")
                }
                if (ch4.isChecked) {
                    lista.add("4")
                }
                if (ch5.isChecked) {
                    lista.add("5")
                }
                if (ch6.isChecked) {
                    lista.add("6")
                }
                if (ch7.isChecked) {
                    lista.add("7")
                }
                if (ch8.isChecked) {
                    lista.add("8")
                }
                if (ch9.isChecked) {
                    lista.add("9")
                }
                if (ch0.isChecked) {
                    lista.add("0")
                }
                database.reference.child("usuarios").child(usuario.text.toString())

                for (i in 0..10) {
                    if (lista.contains("$num")) {
                        Toast.makeText(
                            this,
                            "Se ha creado la Tabla de ${usuario.text}",
                            Toast.LENGTH_SHORT
                        ).show()
                        database.reference.child("usuarios").child(usuario.text.toString())
                            .child("$num").setValue("$num")
                    }
                    num++
                }
                val intent = Intent(this, TableCreator::class.java)
                startActivity(intent)
            }

        }
    }
}

