package com.example.laba_13_video

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.laba_13_video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Используем ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Указываем путь к видеофайлу
        val videoPath = "android.resource://" + packageName + "/" + R.raw.espresso_macchiato
        val videoUri = Uri.parse(videoPath)

        // Настраиваем VideoView через ViewBinding
        binding.videoView.setVideoURI(videoUri)
        binding.videoView.requestFocus()

        // Запускаем воспроизведение видео
        binding.videoView.start()
    }
}