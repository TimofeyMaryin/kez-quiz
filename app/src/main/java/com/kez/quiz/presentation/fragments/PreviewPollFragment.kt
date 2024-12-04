package com.kez.quiz.presentation.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kez.quiz.R
import com.kez.quiz.data.QuizModel
import com.kez.quiz.presentation.navigation.Screen
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.PrimaryBG
import com.kez.quiz.presentation.ui.PrimaryFABIcon
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.black
import com.kez.quiz.ui.theme.cardContainerColor
import com.kez.quiz.ui.theme.darkgrayBrush
import com.kez.quiz.ui.theme.white


/**
 * Ну тут тоже самое (почти), что и в MainFragment().
 *
 * Минутка информации:)
 * Когда идет переход на другой фрагмент (к примеру этот с MainFragment), то прошлый фрагмент
 * полностью уходит из стека, а соответсвенно, когда он не запущен, можно сказать, что его нет и
 * соответсвенно все данные, которые там могли бы вычислиться или получиться стерлись бы. За этим
 * мы использовали ViewModel, которая позволяет сохранять данные вне зависимсоти от изменнения состояния
 * не только конкретной Composable функции (фрагмента), ну а также при изменении абсолютно любого состояния
 * тот же перевот экрана - это тоже изменение состояния, только уже целого Activity и ViewModel может
 * сохранить данные даже при такой встряски ;)
 *
 * Что самое важное, мы ссылаемся на один и тот же ViewModel, которые инициализировали в MainActivity,
 * поэтому он един для каждого фрагмента. Если бы мы6 условно, инициализировали ViewModel в каждой отдельной
 * Composable функции это были бы 2 разные сущности (обьекта или класса), что привело бы к тому, что мы
 * бы постоянно теряли информацию из Firebase.
 */

@Composable
fun PreviewPollFragment(
    viewModel: KezViewModel,
    navController: NavController
) {
    PrimaryBG()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White))
            Row(
                modifier = Modifier.fillMaxSize(.9f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
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

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .fillParentMaxWidth(.9f)
                                .height(250.dp)
                                .background(cardContainerColor),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.advertising_17844586),
                                contentDescription = null,
                                modifier = Modifier.height(139.dp),
                                contentScale = ContentScale.FillHeight
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            AppText(
                                value = viewModel.currentModel.theme,
                                textSize = TextSize.MEDIUM,
                                fontWeight = FWeight.BOLD,
                                color = white
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        Box(
                            modifier = Modifier.fillParentMaxWidth(.9f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            AppText(
                                value = "Вопросы",
                                textSize = TextSize.MEDIUM,
                                fontWeight = FWeight.BOLD,
                                color = black
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    items(viewModel.currentModel.questions) {
                        QuestionItem(model = it)
                    }

                    item {
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier.fillMaxSize(.9f),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PrimaryFABIcon(icon = Icons.Default.ArrowBack, primaryBrush = darkgrayBrush) {
                navController.popBackStack()
            }


            PrimaryFABIcon(icon = Icons.Default.ArrowForward, primaryBrush = cardContainerColor) {
                navController.navigate(Screen.PollScreen.route)
            }
        }
    }
}



@Composable
private fun QuestionItem(
    model: QuizModel,
) {

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .shadow(
                elevation = 10.dp,
                shape = MaterialTheme.shapes.medium,
                spotColor = black.copy(.3f),
                ambientColor = black.copy(.2f)
            )
            .fillMaxWidth(.9f)
            .height(100.dp)
            .background(cardContainerColor),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(black.copy(.4f)))

        AppText(
            value = model.question,
            textSize = TextSize.SMALL,
            fontWeight = FWeight.REGULAR,
            color = white,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(.9f)
        )

    }

}