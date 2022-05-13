package com.example.gymapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.gymapp.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    //private val viewModel: ActivityMainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)

            setContentView(R.layout.activity_login)

        }



    }

}


