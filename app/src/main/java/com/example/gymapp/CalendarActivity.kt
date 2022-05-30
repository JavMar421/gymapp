package com.example.gymapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class CalendarActivity : AppCompatActivity() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_calendar)
            val logout = findViewById<ImageView>(R.id.logoutlogo)
            val edittable = findViewById<ImageView>(R.id.tablaicon)
            val editejer = findViewById<ImageView>(R.id.ejericon)
            val calendar = findViewById<ImageView>(R.id.calendaricon)
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

        }
    }