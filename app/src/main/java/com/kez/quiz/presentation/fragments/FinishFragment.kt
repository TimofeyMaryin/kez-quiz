package com.kez.quiz.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.kez.quiz.data.QuizButtonState
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.CardItem
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.containerColor
import com.kez.quiz.ui.theme.white

@Composable
fun FinishFragment(
    viewModel: KezViewModel,
    navController: NavController,
) {
    var bgcolor by remember { mutableStateOf(Color.Red) }
    LaunchedEffect(Unit) {
        if (viewModel.resultState == QuizButtonState.CORRECT){
            bgcolor = Color.Green
        }
    }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgcolor), contentAlignment = Alignment.Center
        ) {
            AppText(
                value = if(bgcolor == Color.Red)"POSOSAL" else "KRASAVA",
                textSize = TextSize.LARGE,
                fontWeight = FWeight.BOLD,
                color = white
            )
        }
    }

