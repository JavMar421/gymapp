package com.example.gymapp


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TableActivity :AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        val database = Firebase.database("https://gym-proyect-dam-default-rtdb.europe-west1.firebasedatabase.app")
        val siguiente = findViewById<Button>(R.id.Siguiente)
        val anterior = findViewById<Button>(R.id.Anterior)
        val ejernombre = findViewById<TextView>(R.id.nombreejer)
        val videoView: VideoView = findViewById(R.id.videoView)
        val linearvideo = findViewById<LinearLayout>(R.id.linearvideoView)
        val peso = findViewById<TextView>(R.id.pesoejer)
        val series = findViewById<TextView>(R.id.seriesejer)
        val repeticiones = findViewById<TextView>(R.id.repesejer)
        val logoGrande = findViewById<ImageView>(R.id.logoGrande)
        val logout = findViewById<ImageView>(R.id.logoutlogo)
        var num=0
        var check:String
        var lista: MutableList<String> = mutableListOf()
        val listademo: MutableList<String> = mutableListOf("0","1","2","3","4","5","6","7","8","9")


        //Pantalla de Carga
        logoGrande.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            if (lista.isEmpty()){
                delay(3000)
                nodata()
                delay(2000)
                lista=listademo
                logoGrande.visibility = View.GONE
                siguiente.callOnClick()
            }else {
                logoGrande.visibility = View.GONE
                siguiente.callOnClick()
            }
        }

        logout.setOnClickListener{
            Toast.makeText(this,"Cerrando Sesion",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        linearvideo.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.pause()
            }
            else {
                videoView.start()
            }
        }

        //Lectura de Datos de Usuario

        for (i in 0..9){
            database.reference.child("usuarios").child(saveuser).child("$i").get().addOnSuccessListener {
                if (it.value != null) {
                    check=it.value.toString()

                    if (!lista.contains(check)) {
                        lista.add(check)
                    }

                }
            }
        }
        siguiente.setOnClickListener{
            do {
                num++
                if (num>9){num=0}
            }
            while(!lista.contains("$num"))


            database.reference.child("ejercicio").child("$num").child("nombre").get().addOnSuccessListener{
                ejernombre.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("peso").get().addOnSuccessListener{
                peso.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("reps").get().addOnSuccessListener{
                repeticiones.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("serie").get().addOnSuccessListener{
                series.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("video").get().addOnSuccessListener{
                videoView.setVideoPath(it.value.toString())
            }




        }

        anterior.setOnClickListener{
            do {
                num--
                if (num<0){num=9}
            }
            while(!lista.contains("$num"))

            database.reference.child("ejercicio").child("$num").child("nombre").get().addOnSuccessListener{
                ejernombre.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("peso").get().addOnSuccessListener{
                peso.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("reps").get().addOnSuccessListener{
                repeticiones.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("serie").get().addOnSuccessListener{
                series.text=it.value.toString()
            }
            database.reference.child("ejercicio").child("$num").child("video").get().addOnSuccessListener{
                videoView.setVideoPath(it.value.toString())
            }
        }
    }

    private fun nodata() {
        Toast.makeText(this,"$saveuser no tiene Datos, cargando tabla completa",Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"Contacta con un Administrador para una tabla personalizada",Toast.LENGTH_SHORT).show()
    }
}