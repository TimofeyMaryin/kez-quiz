package com.kez.quiz.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow


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
enum class TextSize { SMALL, MEDIUM, LARGE, EXTRA_SMALL }
enum class FWeight { TINY, REGULAR, BOLD }


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
        },
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textDecoration = textDecoration,
        fontStyle = fontStyle,
    )
}