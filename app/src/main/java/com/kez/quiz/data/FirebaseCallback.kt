package com.kez.quiz.data

/**
 * Здесь используется довольно продвинутый архитектурный паттерн callback. Если интересно
 * то можешь это спокойной в интернете найти.
 */

interface FirebaseCallback {
    fun onSuccess(models: List<KezModel>)
    fun onError(msg: String)
}