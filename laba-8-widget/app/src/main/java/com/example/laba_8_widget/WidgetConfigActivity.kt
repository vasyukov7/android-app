package com.example.laba_8_widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class WidgetConfigActivity : Activity() {
    private val URL_KEY = stringPreferencesKey("widget_url")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_config)

        val urlInput = findViewById<EditText>(R.id.url_input)
        val saveButton = findViewById<Button>(R.id.save_button)

        // Загружаем текущий URL (если есть)
        val currentUrl = runBlocking {
            applicationContext.dataStore.data.first()[URL_KEY] ?: "https://google.com"
        }
        urlInput.setText(currentUrl)

        saveButton.setOnClickListener {
            val url = urlInput.text.toString()

            // Сохраняем новый URL в DataStore
            runBlocking {
                applicationContext.dataStore.edit { prefs ->
                    prefs[URL_KEY] = url
                }
            }

            // Получаем ID всех установленных виджетов
            val appWidgetManager = AppWidgetManager.getInstance(this)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(this, WidgetProvider::class.java))

            // Обновляем каждый виджет
            for (id in appWidgetIds) {
                WidgetProvider.updateAppWidget(this, appWidgetManager, id)
            }

            // Закрываем активность
            finish()
        }
    }
}
