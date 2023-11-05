package com.example.testkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Notes: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes) // Establece el diseño XML asociado
        val botonNavegar = findViewById<Button>(R.id.btnGoToQuiz)
        botonNavegar.setOnClickListener {
            val intent = Intent(this, Quiz::class.java)
            startActivity(intent)
        }
    }

}