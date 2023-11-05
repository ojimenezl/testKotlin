package com.example.testkotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView

class Quiz : AppCompatActivity() {
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var countdownTextView: TextView
    private val initialTimeMillis: Long = 61000  // 5 minutos en milisegundos (5 * 60 * 1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)
        countdownTextView = findViewById(R.id.countdownTimer)
        // Inicia el cronómetro con el tiempo inicial
        startCountdownTimer(initialTimeMillis)
        val checkAnswerButton: Button = findViewById(R.id.checkButton)
        val questionsContainer: LinearLayout = findViewById(R.id.questionsContainer)
        val correctAnswers = listOf(0, 2, 0, 2) // Lista de respuestas correctas (supongamos que es 1 para la opción correcta)

        checkAnswerButton.setOnClickListener {
            var correctCount = 0
            var answerCount = 0
            for (i in 0 until questionsContainer.childCount) {
                val child = questionsContainer.getChildAt(i)
                if (child is RadioGroup) {
                    // Este elemento es un RadioGroup, así que ahora itera sobre sus hijos para encontrar los RadioButton.
                    for (j in 0 until child.childCount) {
                        val radioButton = child.getChildAt(j)
                        if (radioButton is RadioButton && radioButton.isChecked) {
                            // Obtiene la opción seleccionada
                            val selectedOptionss = child.findViewById<RadioButton>(child.checkedRadioButtonId)
                            // Obtiene la posición de la opción seleccionada
                            val selectedPosition = child.indexOfChild(selectedOptionss)
                            if (selectedPosition == correctAnswers[answerCount]) {
                                correctCount++
                            }
                            answerCount++

                        }
                    }
                }
            }

            finishQuiz(questionsContainer)
            // Muestra el número de respuestas correctas en un mensaje Toast
            Toast.makeText(this, "Respuestas correctas: $correctCount", Toast.LENGTH_SHORT).show()
        }

    }

    private fun startCountdownTimer(timeMillis: Long) {
        countdownTimer = object : CountDownTimer(timeMillis, 1000) {  // Actualiza cada 1 segundo
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                countdownTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                val questionsContainer: LinearLayout = findViewById(R.id.questionsContainer)
                countdownTextView.text = "00:00"  // Cuando el tiempo se agota
                Toast.makeText(this@Quiz, "Tiempo finalizado", Toast.LENGTH_SHORT).show()
                finishQuiz(questionsContainer)
            }
        }.start()
    }
    private fun finishQuiz(questionsContainer: LinearLayout){
        for (i in 0 until questionsContainer.childCount) {
            val child = questionsContainer.getChildAt(i)
            if (child is RadioGroup) {
                for (j in 0 until child.childCount) {
                    val radioButton = child.getChildAt(j)
                    if (radioButton is RadioButton) {
                        radioButton.isEnabled = false
                    }
                }
            }
        }

    }
}
