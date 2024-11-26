package com.kez.quiz.presentation.navigation


/**
 * Здесь мы определяем все узлы навигации, которые возможны в нашем приложении.
 *
 * Мы используем sealed класс, потому что он идеально подходит для организации подобной навигации.
 * Возможно, у тебя встанет вопрос: можно же использовать ровно также статические классы, но я отвечу,
 * что мы используем простейшую навигацию.
 */

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main-screen")
    data object PreviewPollScreen : Screen("preview-poll-screen")
    data object PollScreen : Screen("poll-screen")
    data object FinishFragment : Screen("finish-screen")
}
