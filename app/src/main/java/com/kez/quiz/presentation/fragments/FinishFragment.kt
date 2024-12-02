package com.kez.quiz.presentation.fragments

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kez.quiz.R
import com.kez.quiz.data.QuizButtonState
import com.kez.quiz.presentation.navigation.Screen
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.CardItem
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.black
import com.kez.quiz.ui.theme.containerColor
import com.kez.quiz.ui.theme.white

@Composable
fun FinishFragment(

    viewModel: KezViewModel,
    navController: NavController,
) {
    BackHandler {
        viewModel.resultState = QuizButtonState.NONE
        navController.navigate(Screen.MainScreen.route)
    }


    var bgcolor by remember { mutableStateOf(Color.Red) }
    LaunchedEffect(Unit) {
        if (viewModel.resultState == QuizButtonState.CORRECT) {
            bgcolor = Color.Green
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgcolor), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (bgcolor == Color.Red) {
                Image(
                    painter = painterResource(id = R.drawable.social_credit_minus),
                    contentDescription = "не будет миска рис и кошка жена",
                    modifier = Modifier.size(200.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.social_credit_plus),
                    contentDescription = "партия выдать миска рис и кошка жена",
                    modifier = Modifier.size(200.dp)
                )
            }



            AppText(
                value = if (bgcolor == Color.Red) "ЧЕЛ..." else "ЛЕГЕНДА",
                textSize = TextSize.LARGE,
                fontWeight = FWeight.BOLD,
                color = white
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clickable { navController.navigate(Screen.MainScreen.route) }
            ) {

            }
        }
    }
    }



