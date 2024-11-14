package com.kez.quiz.presentation.navigation

import com.kez.quiz.domain.FirebaseUtils
import com.kez.quiz.domain.FirebaseUtilsImpl

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main-screen")
    data object PreviewPollScreen : Screen("preview-poll-screen")
}

const val MAIN_SCREEN = "main-screen"
const val PREVIEW_POLL_SCREEN = "preview-poll-screen"
