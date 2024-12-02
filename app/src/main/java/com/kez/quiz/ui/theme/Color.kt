package com.kez.quiz.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val containerColor = Color.Gray
val black = Color.Black
val white = Color.White
val gray = Color.Gray
val green = Color(0xFF45FF00)
val darkgray = Color(0xFF81D5BF)
val yellow = Color.Yellow

val darkgrayBrush = Brush.linearGradient(
    listOf(
        Color(0xFF808080),
        Color(0xFF808080),
    )
)

val cardContainerColor = Brush.linearGradient(
    listOf(
        Color(0xff40c9ff),
        Color(0xffe81cff)
    )
)

val redBrush = Brush.linearGradient(
    listOf(
        Color.Red,
        Color.Red,
    )
)

@Composable fun red() = MaterialTheme.colorScheme.error
