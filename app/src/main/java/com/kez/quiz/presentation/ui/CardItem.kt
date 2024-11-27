package com.kez.quiz.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kez.quiz.data.KezModel
import com.kez.quiz.ui.theme.black
import com.kez.quiz.ui.theme.containerColor


/**
 * На самом деле, мне кажеться не стоит тебе обьяснять, как и что здесь работает
 * ты можешь смело экспеременитровать и гуглить, как работают те или иные функции, думаю, тут проблем не возникнет
 */

@Composable
fun CardItem(
    model: KezModel,
    onClick: () -> Unit
) {

    Box(modifier = Modifier.padding(vertical = 16.dp)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(35.dp))
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {

            Row(
                modifier = Modifier.fillMaxSize(.9f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    AppText(
                        value = model.theme,
                        textSize = TextSize.MEDIUM,
                        fontWeight = FWeight.BOLD,
                        color = black
                    )
                    AppText(value = "${model.questions.size} вопросов", textSize = TextSize.SMALL, fontWeight = FWeight.REGULAR, color = black.copy(.6f))
                }


                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
                    Button(
                        onClick = { onClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Cyan
                        )
                    ) {
                        AppText(
                            value = "Начать",
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
fun HorizontalCardItem(
    model: KezModel,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .height(250.dp)
            .aspectRatio(.6f)
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AppText(
            value = model.theme,
            textSize = TextSize.MEDIUM,
            fontWeight = FWeight.BOLD,
            color = black
        )
    }

}

@Preview
@Composable
private fun CardItemPreview() {
    CardItem(model = KezModel("Math")) {

    }
}