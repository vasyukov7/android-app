package com.example.laba_23_voice

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class VoiceActivity : AppCompatActivity() {

    // Объявляем переменные с правильными ID
    private lateinit var resultTextView: TextView
    private lateinit var voiceInputButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice) // Убедитесь, что это правильный layout

        // Инициализация с проверкой
        resultTextView = findViewById(R.id.resultTextView) ?: throw RuntimeException("TextView not found!")
        voiceInputButton = findViewById(R.id.voiceInputButton) ?: throw RuntimeException("Button not found!")

        voiceInputButton.setOnClickListener {
            startVoiceInput()
        }
    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Говорите...")
        }

        try {
            startActivityForResult(intent, 100)
        } catch (e: Exception) {
            resultTextView.text = "Ошибка: ${e.localizedMessage}"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.firstOrNull()?.let {
                resultTextView.text = "Результат: $it"
            }
        }
    }
}