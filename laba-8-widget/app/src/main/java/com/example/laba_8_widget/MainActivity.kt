package com.example.laba_8_widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val configureButton = findViewById<Button>(R.id.configure_button)
        configureButton.setOnClickListener {
            val intent = Intent(this, WidgetConfigActivity::class.java)

            // Получаем ID всех установленных виджетов
            val appWidgetManager = AppWidgetManager.getInstance(this)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(this, WidgetProvider::class.java))

            // Передаём первый найденный виджет (если есть)
            if (appWidgetIds.isNotEmpty()) {
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[0])
            }

            startActivity(intent)
        }
    }
}
