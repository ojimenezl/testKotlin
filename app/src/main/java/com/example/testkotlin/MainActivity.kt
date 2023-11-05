package com.example.testkotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import android.widget.MediaController
import android.media.MediaPlayer
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val videoView = findViewById<VideoView>(R.id.videoView)
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.curtkobain) // Reemplaza "nombre_del_video" con el nombre de tu archivo de video en la carpeta "res/raw"
        val mediaController = MediaController(this)
        val botonNavegar = findViewById<Button>(R.id.btnGoToNotes)

        videoView.setVideoURI(videoUri)
        videoView.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoView)

        videoView.setOnPreparedListener { mp: MediaPlayer? ->
            videoView.start()
        }
        botonNavegar.setOnClickListener {
            val intent = Intent(this, Notes::class.java)
            startActivity(intent)
        }

    }
}