package com.kez.quiz.data

import com.google.firebase.database.PropertyName

data class KezModel(
    @PropertyName("theme") val theme: String = "",
    @PropertyName("questions") val questions: List<QuizModel> = emptyList(),
)

data class QuizModel(
    @PropertyName("question") val question: String = "",
    @PropertyName("answers") val answers: List<String> = emptyList(),
    @PropertyName("currentIndex") val currentIndex: Int = 0,
)

