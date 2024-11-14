package com.kez.quiz.data

import com.google.firebase.database.PropertyName


/**
 * Вот здесь у нас идет представление модели данных из Firebase. Мы не можем просто так взять
 * и использовать как нам вздумается данные из FB не преобразовав это в Kotlin объект. Для этого
 * мы используем data class - отличный инструмент для описание моделей обьектов. Да, мы можем использовать
 * обычные классы, но это мавитон и пусть это будет в языках по типу Java, где подобного нет (на самом деле
 * есть, но с небольшими условностями))
 *
 * Аннотация PropertyName - является частью Firebase, которая помогает разработчику правильно ловить данные из
 * Firebase. Все описанные поля в PropertyName совпадают с полями из Forebase, а также способ построения
 * модели полностью совпадает со способом построения модели в Firebase
 *
 * Проще говоря, модели из FB должны совпадать с Kotlin моделью KezModel
 */
data class KezModel(
    @PropertyName("theme") val theme: String = "",
    @PropertyName("questions") val questions: List<QuizModel> = emptyList(),
)

data class QuizModel(
    @PropertyName("question") val question: String = "",
    @PropertyName("answers") val answers: List<String> = emptyList(),
    @PropertyName("currentIndex") val currentIndex: Int = 0,
)

