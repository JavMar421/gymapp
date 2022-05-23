package com.example.gymapp


import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
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

        var siguiente = findViewById<Button>(R.id.Siguiente)
        var anterior = findViewById<Button>(R.id.Anterior)
        var ejernombre = findViewById<TextView>(R.id.nombreejer)
        var videoView: VideoView = findViewById(R.id.videoView)
        var linearvideo = findViewById<LinearLayout>(R.id.linearvideoView)
        var peso = findViewById<TextView>(R.id.pesoejer)
        var series = findViewById<TextView>(R.id.seriesejer)
        var repeticiones = findViewById<TextView>(R.id.repesejer)
        val logoGrande = findViewById<ImageView>(R.id.logoGrande)
        val mediaController: MediaController = MediaController(this)
        var num=1
        mediaController.setAnchorView(videoView)

        logoGrande.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            logoGrande.visibility = View.GONE

        }

        linearvideo.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.pause()
            }
            else {
                videoView.start()
            }
        }

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

        siguiente.setOnClickListener{
            num++
            if (num>9 || num<0){num=0}

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
            num--
            if (num<0||num>9){num=9}

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
}