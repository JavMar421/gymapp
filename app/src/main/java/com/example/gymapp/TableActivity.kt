package com.example.gymapp


import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class TableActivity :AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        var siguiente = findViewById<Button>(R.id.Siguiente)
        var anterior = findViewById<Button>(R.id.Anterior)
        var videoView: VideoView = findViewById(R.id.videoView)
        val mediaController: MediaController = MediaController(this)


        mediaController.setAnchorView(videoView)

        videoView.setVideoPath("https://firebasestorage.googleapis.com/v0/b/gym-proyect-dam.appspot.com/o/CR7%20wine.mp4?alt=media&token=32be4218-e98a-4964-b732-056a90e90467")
        videoView.setOnClickListener{videoView.start()}

        siguiente.setOnClickListener{
            videoView.setVideoPath("https://firebasestorage.googleapis.com/v0/b/gym-proyect-dam.appspot.com/o/CR7%20wine.mp4?alt=media&token=32be4218-e98a-4964-b732-056a90e90467")
        }
        anterior.setOnClickListener{
        videoView.setVideoPath("https://firebasestorage.googleapis.com/v0/b/gym-proyect-dam.appspot.com/o/guayando.mp4?alt=media&token=a7a12258-7106-4de7-943b-be1d021625b3")
        }

    }
}