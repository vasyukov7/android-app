package com.example.gestureapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Получение контекста приложения
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertEquals("com.example.gestureapp", context.packageName)
    }
}
