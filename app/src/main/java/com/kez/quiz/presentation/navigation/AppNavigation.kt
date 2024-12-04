package com.kez.quiz.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kez.quiz.presentation.fragments.FinishFragment
import com.kez.quiz.presentation.fragments.MainFragment
import com.kez.quiz.presentation.fragments.PollFragment
import com.kez.quiz.presentation.fragments.PreviewPollFragment
import com.kez.quiz.presentation.vms.KezViewModel


/**
 * Пу-пу-пу
 *
 * Здесь нужно познакомиться с термином Рекомпозиция - те пересоздания конкретный Composable функций
 * Так вот, рекомпозиция может происходит по двум причинам:
 * 1. Изменяется состояние родительского Activity или обьекта
 * 2. Программно происходит рекомпозиция, путем изменения специальных mutableState переменных.
 * Как работают mutableState переменные в этом контексте: когда данная переменная ловит изменение значения
 * она требует Composable функцию вызваться по новой. Не забывай, что Composable функция, просто функция
 * и она работает также как и обычная функция - выполнилась и потухла
 *
 * Здесь роль этой переменной играет navController типа  NavHostController
 * когда во фрагментах мы используем метод этой переменной navigate(destination: String) мы явно указываем:
 * a) какую composable(route: String) фнкцию надо вызвать
 * б) совершить рекомпозицию данной функции, чтоб вызвалась функция внутри composable(route: String)
 *
 *
 *  Также, переменная navController аналогична ViewModel, которая способна хранить состояния вне
 *  зависимости от изменения состояния родительского Activity, поэтому если у нас сейчас PreviewPollFragment
 *  и мы повернем экран, то останется PreviewPollFragment, а не будет возврат к настройки по умолчанию,
 *  где должен вызваться MainFragment()
 */

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

        this.composable(
            route = Screen.PollScreen.route
        ) {
            PollFragment(viewModel = viewModel, navController = navController)
        }

        this.composable(
            route = Screen.FinishFragment.route
        ) {
            FinishFragment(viewModel = viewModel, navController = navController)
        }

    }
}

