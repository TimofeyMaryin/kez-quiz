package com.kez.quiz.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import com.kez.quiz.ui.theme.violet
import com.kez.quiz.ui.theme.white

@Composable
fun PrimaryBG() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(white),
        contentAlignment = Alignment.Center,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(violet.copy(.5f), violet.copy(.2f), violet.copy(.001f)),
                        ),
                    ),
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(.9f),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(
                            Brush.radialGradient(
                                listOf(violet.copy(.6f), violet.copy(.2f)),
                            ),
                        ),
                )
            }

            Box(modifier = Modifier
                .weight(.1f)
                .fillMaxSize())
        }
    }
}