package com.kez.quiz.presentation.fragments

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kez.quiz.R
import com.kez.quiz.presentation.navigation.Screen
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.CardItem
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.HorizontalCardItem
import com.kez.quiz.presentation.ui.PrimaryBG
import com.kez.quiz.presentation.ui.SecondaryButton
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.black
import com.kez.quiz.ui.theme.cardContainerColor
import com.kez.quiz.ui.theme.violet
import com.kez.quiz.ui.theme.white


/**
 * Это главнй экран, который будет всегда отображаться первым при запуске приложения.
 *
 * Что и как тут фурычит:
 * Переменная configuration отвечает за состояния поворота экрана. Те, если экран перевернулся
 * переменная это фиксирует и это можно считать, что мы и сделали.
 *
 * infiniteProgression и progress лишь отвечают за то, чтоб индикатор загрузки корректно работал.
 *
 * Конструкция when как раз нужна для того, чтоб правильно обработать состояние изменения экрана.
 *
 * HorizontalOrientationMainScreen и VerticalOrientationMainScreen - просто функции, которые создают
 * интерфейс.
 */

@Composable
fun MainFragment(
    navController: NavController,
    viewModel: KezViewModel
) {
    val configuration = LocalConfiguration.current
    val infiniteProgression = rememberInfiniteTransition()
    val progress by infiniteProgression.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(500),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    PrimaryBG()

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE  -> {
            HorizontalOrientationMainScreen(progress = progress, viewModel = viewModel, navController = navController)
        }
        else -> {
            VerticalOrientationMainScreen(
                progress = progress,
                viewModel = viewModel,
                navController = navController
            )
        }

    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun VerticalOrientationMainScreen(
    navController: NavController,
    progress: Float,
    viewModel: KezViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        if (viewModel.isDoneLoad) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(.9f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center
                            ) {
                                AppText(
                                    value = "Здарова, Кент !",
                                    textSize = TextSize.SMALL,
                                    fontWeight = FWeight.BOLD,
                                    color = black
                                )
                                AppText(
                                    value = "Давай проверим твои знания!",
                                    textSize = TextSize.EXTRA_SMALL,
                                    fontWeight = FWeight.REGULAR,
                                    color = black
                                )
                            }


                        }

                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(35.dp))
                            .fillParentMaxWidth(.9f)
                            .height(250.dp)
                            .background(cardContainerColor,),
                            contentAlignment = Alignment.BottomCenter,
                    ) {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(black.copy(.4f)))

                        Row(
                            modifier = Modifier.fillMaxSize(.9f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(painter = painterResource(id = R.drawable.ryan), contentDescription = null, modifier = Modifier.height(130.dp) .width(120.dp),  contentScale = ContentScale.FillHeight)

                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                AppText(
                                    value = "Случайная тема)) Если сам не можешь решиться))",
                                    textSize = TextSize.SMALL,
                                    fontWeight = FWeight.REGULAR,
                                    color = white
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                SecondaryButton(value = "Начать") {
                                    viewModel.currentModel = viewModel.cards.random()
                                    navController.navigate(Screen.PreviewPollScreen.route)
                                }



                            }
                        }
                    }
                }

                items(viewModel.cards) { model ->
                    CardItem(model = model) {
                        viewModel.currentModel = model
                        navController.navigate(Screen.PreviewPollScreen.route)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }

        } else {
            CircularProgressIndicator(progress = progress)
        }
    }
}


@Composable
private fun HorizontalOrientationMainScreen(
    navController: NavController,
    progress: Float,
    viewModel: KezViewModel
) {
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.bg_bggenerator_com_qwerty),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    if (viewModel.isDoneLoad) {
        Box(modifier = Modifier.fillMaxSize(.9f), contentAlignment = Alignment.Center) {

            LazyRow(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(viewModel.cards) {
                    HorizontalCardItem(model = it) {
                        viewModel.currentModel = it
                        navController.navigate(Screen.PreviewPollScreen.route)
                    }
                }
            }
        }
    } else {
        CircularProgressIndicator(progress = progress)
    }

}