package com.example.gymapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.UserApplication.Companion.datos

class CalendarActivity : AppCompatActivity() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_calendar)
            val logout = findViewById<ImageView>(R.id.logoutlogo)
            val edittable = findViewById<ImageView>(R.id.tablaicon)
            val editejer = findViewById<ImageView>(R.id.ejericon)
            val calendaricon = findViewById<ImageView>(R.id.calendaricon)
            val calendar = findViewById<CalendarView>(R.id.calendar)
            val siguiente = findViewById<Button>(R.id.Siguiente)
            val anterior = findViewById<Button>(R.id.Anterior)

            logout.setOnClickListener{
                datos.wipe()
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
            calendaricon.setOnClickListener{
                /*val intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)*/
            }
            var cosa:String

            calendar.setOnDateChangeListener { _, i, i1, i2 ->
                cosa=(i2.toString()+"/"+(i1+1)+"/"+i)
                Toast.makeText(this,cosa,Toast.LENGTH_SHORT).show()

            }

            calendar.date

            siguiente.setOnClickListener {
                calendar.setDate(calendar.date)
                Toast.makeText(this,"",Toast.LENGTH_SHORT).show()

            }
            anterior.setOnClickListener {

            }

        }
    }