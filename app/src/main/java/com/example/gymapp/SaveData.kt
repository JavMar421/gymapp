package com.example.gymapp

import android.content.Context

class SaveData(val context: Context) {
    val SHARED_NAME = "GymDB"
    val SHARED_USER = "user"
    val SHARED_PASS = "pass"
    val dato=context.getSharedPreferences(SHARED_NAME, 0)
    fun saveName(user:String){
        dato.edit().putString(SHARED_USER,user).apply()
    }
    fun savePass(pass:String){
        dato.edit().putString(SHARED_PASS,pass).apply()
    }
    fun getName():String{
        return dato.getString(SHARED_USER,"")!!
    }
    fun getPass(): String{
        return dato.getString(SHARED_PASS,"")!!
    }
    fun wipe(){
        dato.edit().clear().apply()
    }

}
