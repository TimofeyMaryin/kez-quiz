package com.kez.quiz.presentation.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kez.quiz.data.QuizModel
import com.kez.quiz.presentation.ui.AppText
import com.kez.quiz.presentation.ui.FWeight
import com.kez.quiz.presentation.ui.TextSize
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.ui.theme.black

@Composable
fun PreviewPollFragment(
    viewModel: KezViewModel,
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(9f),
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
                items(viewModel.currentModel.questions) {
                    QuestionItem(model = it)
                }

                item {
                    Button(onClick = {  }) {
                        AppText(
                            value = "Начать контроль",
                            textSize = TextSize.MEDIUM,
                            fontWeight = FWeight.REGULAR,
                            color = black
                        )
                    }
                }

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
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        AppText(
            value = model.question,
            textSize = TextSize.SMALL,
            fontWeight = FWeight.REGULAR,
            color = black,
            textAlign = TextAlign.Center
        )

    }

}