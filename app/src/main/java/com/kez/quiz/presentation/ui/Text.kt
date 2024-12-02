package com.kez.quiz.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.kez.quiz.R


/**
 * Здесь я просто обьясню из гугла, что такое enum класс
 *
 * Enums или перечисления представляют тип данных, который позволяет определить набор логически
 * связанных констант. Для определения перечисления применяются ключевые слова enum class.
 *
 * Проще говоря, они нужны для строгого перечесления.
 *
 * https://metanit.com/kotlin/tutorial/4.13.php
 */

private val fontFamily = FontFamily(
    listOf(
        Font(R.font.montserratalternates_black, weight = FontWeight.Bold,),
        Font(R.font.montserratalternates_light, weight = FontWeight.Light),
        Font(R.font.montserratalternates_medium, weight = FontWeight.Medium),
        Font(R.font.montserratalternates_regular)
    )
)

enum class TextSize { SMALL, MEDIUM, LARGE, EXTRA_SMALL }
enum class FWeight { TINY, REGULAR, BOLD, MEDIUM }


@Composable
fun AppText(
    modifier: Modifier = Modifier,
    value: String,
    textSize: TextSize,
    fontWeight: FWeight,
    color: Color,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration? = null,
    fontStyle: FontStyle? = null,
) {
    Text(
        text = value,
        color = color,
        textAlign = textAlign,
        modifier = modifier,
        style = when (textSize) {
            TextSize.SMALL -> MaterialTheme.typography.bodyLarge
            TextSize.MEDIUM -> MaterialTheme.typography.titleLarge
            TextSize.LARGE -> MaterialTheme.typography.displaySmall
            TextSize.EXTRA_SMALL -> MaterialTheme.typography.labelMedium
        },
        fontWeight = when (fontWeight) {
            FWeight.TINY -> FontWeight.Light
            FWeight.REGULAR -> FontWeight.Normal
            FWeight.BOLD -> FontWeight.Bold
            FWeight.MEDIUM -> FontWeight.Medium
        },
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textDecoration = textDecoration,
        fontStyle = fontStyle,
        fontFamily = fontFamily
    )
}


@Composable
fun AppText(
    modifier: Modifier = Modifier,
    value: String,
    textSize: TextSize,
    fontWeight: FWeight,
    color: Brush,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration? = null,
    fontStyle: FontStyle? = null,
) {
    Text(
        text = value,
        textAlign = textAlign,
        modifier = modifier,
        style = TextStyle(
            brush = color,
            fontSize = when (textSize) {
                TextSize.SMALL -> 18.sp
                TextSize.MEDIUM -> 24.sp
                TextSize.LARGE -> 32.sp
                TextSize.EXTRA_SMALL -> 48.sp
            }
        ),
        fontWeight = when (fontWeight) {
            FWeight.TINY -> FontWeight.Light
            FWeight.REGULAR -> FontWeight.Normal
            FWeight.BOLD -> FontWeight.Bold
            FWeight.MEDIUM -> FontWeight.Medium
        },
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textDecoration = textDecoration,
        fontStyle = fontStyle,
        fontFamily = fontFamily
    )
}