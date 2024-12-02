package com.kez.quiz.presentation.fragments


import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kez.quiz.data.QuizButtonState
import com.kez.quiz.presentation.navigation.Screen
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.black
import com.kez.quiz.ui.theme.cardContainerColor
import com.kez.quiz.ui.theme.darkgrayBrush
import com.kez.quiz.ui.theme.redBrush
import com.kez.quiz.ui.theme.white
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PollFragment(
    viewModel: KezViewModel,
    navController: NavController,
) {
    var  currentAnswerCounter by remember { mutableStateOf(0) }

    val context = LocalContext.current
    val pagerState = rememberPagerState { viewModel.currentModel.questions.size }
    val pollModelAnswersStatus = remember { mutableStateListOf<Boolean>() }
    var pollModelQuestionStatus by remember { mutableStateOf(false) }
    var isCanShowAnswers by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    BackHandler {
        viewModel.resultState = QuizButtonState.NONE
        navController.popBackStack()
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        pollModelAnswersStatus.clear()
        pollModelQuestionStatus = false


        repeat(viewModel.currentModel.questions[pagerState.currentPage].answers.size) {
            pollModelAnswersStatus.add(false)
        }

        isCanShowAnswers = true

        for (i in pollModelAnswersStatus.indices) {
            delay(500) // Задержка между появлением вопросов
            pollModelAnswersStatus[i] = true
        }
        pollModelQuestionStatus = true
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
                .background(cardContainerColor),
            contentAlignment = Alignment.Center
        ) {


            Row(
                modifier = Modifier.fillMaxSize(.9f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                AppText(
                    value = viewModel.currentModel.theme,
                    textSize = TextSize.MEDIUM,
                    fontWeight = FWeight.BOLD,
                    color = black
                )
                Spacer(modifier = Modifier.size(30.dp))
            }
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

                        Spacer(modifier = Modifier.height(50.dp))

                        AnimatedVisibility(
                            visible = pollModelQuestionStatus,
                            enter = fadeIn(tween(500)),
                            exit = fadeOut(tween(500))
                        ) {

                            Box(
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.medium)
                                    .fillMaxWidth()
                                    .background(cardContainerColor),
                                contentAlignment = Alignment.Center,
                            ) {

                                AppText(
                                    value = viewModel.currentModel.questions[pagerState.currentPage].question,
                                    textSize = TextSize.MEDIUM,
                                    fontWeight = FWeight.BOLD,
                                    color = white,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(vertical = 36.dp)
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            items(viewModel.currentModel.questions[pagerState.currentPage].answers.size) { index ->
                                var localStatus by remember { mutableStateOf(QuizButtonState.NONE) }
                                PollButton(
                                    value = viewModel.currentModel.questions[pagerState.currentPage].answers[index],
                                    status = localStatus,
                                    isShow = pollModelAnswersStatus.getOrElse(index) { false }
                                ) {
                                    scope.launch {
                                        if (viewModel.currentModel.questions[pagerState.currentPage].currentIndex == index){
                                            localStatus = QuizButtonState.CORRECT
                                            currentAnswerCounter++
                                        } else{
                                            localStatus = QuizButtonState.INCORRECT
                                        }

                                        delay(1_000)


                                        if (pagerState.currentPage == viewModel.currentModel.questions.lastIndex) {
                                            if (currentAnswerCounter > viewModel.currentModel.questions.size/2){
                                                viewModel.resultState = QuizButtonState.CORRECT
                                            } else {
                                                viewModel.resultState = QuizButtonState.INCORRECT
                                            }
                                            navController.navigate(Screen.FinishFragment.route)
                                        }
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
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



    // Модифайры можешь изменять и баловаться!
    AnimatedVisibility(
        visible = isShow,
        enter = slideInHorizontally(tween(200)) { it } + fadeIn(tween(200)),
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
                    .background(
                        when (status) {
                            QuizButtonState.NONE -> darkgrayBrush // вот тут
                            QuizButtonState.CORRECT -> cardContainerColor // тут
                            QuizButtonState.INCORRECT -> redBrush // тут
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                // С текстом можешь покрасоваться
                AppText(
                    modifier = Modifier.fillMaxWidth(.9f),
                    value = value,
                    textSize = TextSize.MEDIUM,
                    fontWeight = FWeight.MEDIUM,
                    color = black,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }



}

