package com.kez.quiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kez.quiz.presentation.fragments.MainFragment
import com.kez.quiz.presentation.fragments.PreviewPollFragment
import com.kez.quiz.presentation.vms.KezViewModel

@Composable
fun AppNavigation(viewModel: KezViewModel) {

    val navController = rememberNavController()

    
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {

        this.composable(
            route = Screen.MainScreen.route
        ) {
            MainFragment(navController = navController, viewModel = viewModel)
        }

        this.composable(
            route = Screen.PreviewPollScreen.route
        ) {
            PreviewPollFragment(navController = navController, viewModel = viewModel)
        }

    }
}

