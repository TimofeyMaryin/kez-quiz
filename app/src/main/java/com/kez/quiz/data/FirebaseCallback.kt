package com.kez.quiz.data

interface FirebaseCallback {
    fun onSuccess(models: List<KezModel>)
    fun onError(msg: String)
}