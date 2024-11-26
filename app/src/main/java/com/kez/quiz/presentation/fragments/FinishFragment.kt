package com.kez.quiz.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.white

@Composable
fun FinishFragment(
    viewModel: KezViewModel,
    navController: NavController,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red), contentAlignment = Alignment.Center) {
        AppText(value = "AYP", textSize = TextSize.LARGE, fontWeight = FWeight.BOLD, color = white)
    }
}