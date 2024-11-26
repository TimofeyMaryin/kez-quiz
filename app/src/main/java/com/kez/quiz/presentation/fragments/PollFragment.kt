package com.kez.quiz.presentation.fragments

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kez.quiz.data.QuizButtonState
import com.kez.quiz.data.QuizModel
import com.kez.quiz.presentation.navigation.Screen
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.gray
import com.kez.quiz.ui.theme.black
import com.kez.quiz.ui.theme.green
import com.kez.quiz.ui.theme.red
import com.kez.quiz.ui.theme.white
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PollFragment(
    viewModel: KezViewModel,
    navController: NavController,
) {

    // НЕ ТРОГАТЬ!

    val context = LocalContext.current
    val pagerState = rememberPagerState { viewModel.currentModel.questions.size }
    val pollModelQuestionStatus = remember { mutableStateListOf<Boolean>() }
    var isCanShowAnswers by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        Log.e("TAG", "PollFragment: ${viewModel.currentModel.questions[pagerState.currentPage].answers.size}")

        // Сбрасываем статус кнопок
        pollModelQuestionStatus.clear()
        repeat(viewModel.currentModel.questions[pagerState.currentPage].answers.size) {
            pollModelQuestionStatus.add(false)
        }

        isCanShowAnswers = true

        // Последовательное обновление статусов с задержкой
        for (i in pollModelQuestionStatus.indices) {
            Log.e("TAG", "PollFragment: $i")
            delay(500) // Задержка между появлением вопросов
            pollModelQuestionStatus[i] = true
        }
    }




    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .background(gray),
            contentAlignment = Alignment.Center
        ) {


            // топ бар заполнить
        }

        Box(
            modifier = Modifier.fillMaxSize(.9f),
            contentAlignment = Alignment.Center
        ) {

            if (isCanShowAnswers) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = false
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        AppText(
                            value = viewModel.currentModel.questions[pagerState.currentPage].question,
                            textSize = TextSize.LARGE,
                            fontWeight = FWeight.BOLD,
                            color = black
                        )


                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            items(viewModel.currentModel.questions[pagerState.currentPage].answers.size) { index ->
                                var localStatus by remember { mutableStateOf(QuizButtonState.NONE) }
                                PollButton(
                                    value = viewModel.currentModel.questions[pagerState.currentPage].answers[index],
                                    status = localStatus,
                                    isShow = pollModelQuestionStatus.getOrElse(index) { false }
                                ) {
                                    scope.launch {
                                        localStatus = if (viewModel.currentModel.questions[pagerState.currentPage].currentIndex == index) {
                                            QuizButtonState.CORRECT
                                        } else {
                                            QuizButtonState.INCORRECT
                                        }

                                        delay(1_000)


                                        if (pagerState.currentPage == viewModel.currentModel.questions.lastIndex) {
                                            navController.navigate(Screen.FinishFragment.route)
                                        }
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
                                    Toast.makeText(context, "Ответ выбран: ${viewModel.currentModel.questions[pagerState.currentPage].answers[index]}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                    }

                }

            }

        }
    }
}


@Composable
private fun PollButton(
    value: String,
    isShow: Boolean,
    status: QuizButtonState,
    onClick: () -> Unit,
) {


    // вот это можешь изменять (цвета!!!)
    val containerColor by animateColorAsState(
        targetValue = when (status) {
            QuizButtonState.NONE -> gray // вот тут
            QuizButtonState.CORRECT -> green // тут
            QuizButtonState.INCORRECT -> red() // тут
        }
    )


    // Модифайры можешь изменять и баловаться!
    AnimatedVisibility(
        visible = isShow,
        enter = slideInHorizontally(tween(500)) { it } + fadeIn(tween(500)),
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable { onClick() }
                    .background(containerColor),
                contentAlignment = Alignment.Center
            ) {
                // С текстом можешь покрасоваться
                AppText(
                    value = value,
                    textSize = TextSize.SMALL,
                    fontWeight = FWeight.MEDIUM,
                    color = black
                )
            }
        }
    }


}

