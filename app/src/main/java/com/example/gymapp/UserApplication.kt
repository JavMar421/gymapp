package com.example.gymapp

import android.annotation.SuppressLint
import android.app.Application

class UserApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var datos:SaveData
    }
    override fun onCreate() {
        super.onCreate()
        datos= SaveData(applicationContext)
    }
}