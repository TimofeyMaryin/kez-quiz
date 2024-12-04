package com.kez.quiz.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.kez.quiz.presentation.navigation.Screen
import com.kez.quiz.ui.theme.cardContainerColor
import com.kez.quiz.ui.theme.white

@Composable
fun PrimaryFABIcon(
    icon: ImageVector,
    primaryBrush: Brush,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(5.dp, white, CircleShape)
            .size(80.dp)
            .background(primaryBrush)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = white,
            modifier = Modifier.size(35.dp)
        )
    }
}