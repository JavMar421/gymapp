package com.example.gymapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.databinding.ActivityTableBinding
import com.google.android.material.appbar.AppBarLayout

class TableActivity :AppCompatActivity() {
    lateinit var binding: ActivityTableBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
    }
}