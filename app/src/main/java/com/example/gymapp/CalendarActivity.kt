package com.example.gymapp

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.databinding.ActivityCalendarBinding

class CalendarActivity : AppCompatActivity() {
        lateinit var binding: ActivityCalendarBinding
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_calendar)
        }
    }