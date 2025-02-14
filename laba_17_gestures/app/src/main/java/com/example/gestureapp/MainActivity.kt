package com.example.gestureapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.unit.dp
import com.example.gestureapp.ui.theme.GestureAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestureAppTheme {
                GestureScreen()
            }
        }
    }
}

@Composable
fun GestureScreen() {
    var gestureText by remember { mutableStateOf("Ожидание жеста...") }
    var backgroundColor by remember { mutableStateOf(Color.LightGray) }
    var doubleTouchDragActive by remember { mutableStateOf(false) }
    var sizeFactor by remember { mutableStateOf(1f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        gestureText = "Одиночное касание (Tap)"
                        backgroundColor = Color.Cyan
                    },
                    onDoubleTap = {
                        gestureText = "Двойное касание (Double Tap)"
                        backgroundColor = Color.Blue
                    },
                    onLongPress = {
                        gestureText = "Долгое нажатие (Long Press)"
                        backgroundColor = Color.Green
                    }
                )
            }

            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { doubleTouchDragActive = true },
                    onDragEnd = {
                        doubleTouchDragActive = false
                    },
                    onDrag = { change, dragAmount ->
                        if (doubleTouchDragActive) {
                            change.consume()
                            if (dragAmount.y > 10f) {
                                sizeFactor *= 1.05f
                                gestureText = "Double Touch Drag - Увеличение"
                            } else if (dragAmount.y < -10f) {
                                sizeFactor *= 0.95f
                                gestureText = "Double Touch Drag - Уменьшение"
                            }
                        }
                    }
                )
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    if (zoom > 1f) {
                        gestureText = "Разведение пальцев (Pinch Open)"
                        backgroundColor = Color.Magenta
                    } else if (zoom < 1f) {
                        gestureText = "Сведение пальцев (Pinch Close)"
                        backgroundColor = Color.Red
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Text(
                text = gestureText,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            )
        }
    }
}
