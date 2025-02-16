package com.example.laba_8_widget

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore("widget_prefs")
