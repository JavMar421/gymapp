package com.example.gymapp


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gymapp.UserApplication.Companion.datos
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class TableActivityV2 :AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table2)
        val database = Firebase.database("https://gym-proyect-dam-default-rtdb.europe-west1.firebasedatabase.app")
        val siguiente = findViewById<Button>(R.id.Siguiente)
        val anterior = findViewById<Button>(R.id.Anterior)
        val ejernombre = findViewById<TextView>(R.id.nombreejer)
        val videoView: VideoView = findViewById(R.id.videoView)
        val linearvideo = findViewById<LinearLayout>(R.id.linearvideoView)
        val linearMenu = findViewById<LinearLayout>(R.id.menu)
        val peso = findViewById<TextView>(R.id.pesoejer)
        val series = findViewById<TextView>(R.id.seriesejer)
        val repeticiones = findViewById<TextView>(R.id.repesejer)
        val logoGrande = findViewById<ImageView>(R.id.logoGrande)
        val edittable = findViewById<ImageView>(R.id.tablaicon)
        val editejer = findViewById<ImageView>(R.id.ejericon)
        val calendar = findViewById<ImageView>(R.id.calendaricon)
        val logout = findViewById<ImageView>(R.id.logoutlogo)
        var num=0
        var datoss=false
        val listadatos: MutableList<String> = mutableListOf()
        val listademo: MutableList<String> = mutableListOf("0","1","2","3","4","5","6","7","8","9")

        if (adminmode){
            linearMenu.visibility = View.VISIBLE
        }
        else{linearMenu.visibility = View.GONE}
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
        //Pantalla de Carga
        logoGrande.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            if (!datoss){
                nodata()
                delay(1000)
                logoGrande.visibility = View.GONE
                siguiente.callOnClick()
            }else {
                logoGrande.visibility = View.GONE
                siguiente.callOnClick()
                anterior.callOnClick()
            }
        }

        logout.setOnClickListener{
            datos.wipe()
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

        database.reference.child("usuarios").child(saveuser).child("0").get().addOnSuccessListener {
                if (it.value==null){
                    Toast.makeText(this,"no se encontraron datos de Usuario",Toast.LENGTH_SHORT).show()
                }
                else{
                datoss=true
                    for (i in 0..9){
                        database.reference.child("usuarios").child(saveuser).child("$i").get().addOnSuccessListener {
                            if (it.value != null) {
                                listadatos.add("$i")
                                }
                            }
                        }
                    }

                }

        siguiente.setOnClickListener{
            if (!datoss) {
                do {
                    num++
                    if (num > 9) {
                        num = 0
                    }
                } while (!listademo.contains("$num"))


                database.reference.child("ejercicio").child("$num").child("nombre").get()
                    .addOnSuccessListener {
                        ejernombre.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("peso").get()
                    .addOnSuccessListener {
                        peso.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("reps").get()
                    .addOnSuccessListener {
                        repeticiones.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("serie").get()
                    .addOnSuccessListener {
                        series.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("video").get()
                    .addOnSuccessListener {
                        videoView.setVideoPath(it.value.toString())
                    }


            }
            else {
                num++
                if (num>=listadatos.size){num=0}
                database.reference.child("usuarios").child(saveuser).child("$num").child("nombre").get().addOnSuccessListener {
                        ejernombre.text = it.value.toString()
                    }
                database.reference.child("usuarios").child(saveuser).child("$num").child("peso").get().addOnSuccessListener {
                        peso.text = it.value.toString()
                    }
                database.reference.child("usuarios").child(saveuser).child("$num").child("reps").get().addOnSuccessListener {
                        repeticiones.text = it.value.toString()
                    }
                database.reference.child("usuarios").child(saveuser).child("$num").child("serie").get().addOnSuccessListener {
                        series.text = it.value.toString()
                    }
                database.reference.child("usuarios").child(saveuser).child("$num").child("video").get().addOnSuccessListener {
                        videoView.setVideoPath(it.value.toString())
                    }

            }
        }

        anterior.setOnClickListener{
            if (!datoss){
                do {
                    num--
                    if (num < 0) {
                        num = listademo.size
                    }
                } while (!listademo.contains("$num"))

                database.reference.child("ejercicio").child("$num").child("nombre").get()
                    .addOnSuccessListener {
                        ejernombre.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("peso").get()
                    .addOnSuccessListener {
                        peso.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("reps").get()
                    .addOnSuccessListener {
                        repeticiones.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("serie").get()
                    .addOnSuccessListener {
                        series.text = it.value.toString()
                    }
                database.reference.child("ejercicio").child("$num").child("video").get()
                    .addOnSuccessListener {
                        videoView.setVideoPath(it.value.toString())
                    }
            }
            else{
                if (num<=0){num=listadatos.size}
                num--

                database.reference.child("usuarios").child(saveuser).child("$num").child("nombre").get().addOnSuccessListener {
                    ejernombre.text = it.value.toString()
                }
                database.reference.child("usuarios").child(saveuser).child("$num").child("peso").get().addOnSuccessListener {
                    peso.text = it.value.toString()
                }
                database.reference.child("usuarios").child(saveuser).child("$num").child("reps").get().addOnSuccessListener {
                    repeticiones.text = it.value.toString()
                }
                database.reference.child("usuarios").child(saveuser).child("$num").child("serie").get().addOnSuccessListener {
                    series.text = it.value.toString()
                }
                database.reference.child("usuarios").child(saveuser).child("$num").child("video").get().addOnSuccessListener {
                    videoView.setVideoPath(it.value.toString())
                }

            }
        }
    }

    private fun nodata() {
        Toast.makeText(this,"$saveuser no tiene Datos, cargando tabla completa",Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"Contacta con un Administrador para una tabla personalizada",Toast.LENGTH_SHORT).show()
    }
}