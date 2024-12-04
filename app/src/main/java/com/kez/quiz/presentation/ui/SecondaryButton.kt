package com.kez.quiz.presentation.ui

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kez.quiz.ui.theme.white

@Composable
fun SecondaryButton(
    value: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black.copy(.6f)
        )
    ) {
        AppText(
            value = value,
            textSize = TextSize.MEDIUM,
            fontWeight = FWeight.REGULAR,
            color = white
        )
    }
}