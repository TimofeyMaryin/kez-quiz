package com.kez.quiz.presentation.vms

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.kez.quiz.data.FirebaseCallback
import com.kez.quiz.data.KezModel
import com.kez.quiz.domain.FirebaseUtilsImpl

class KezViewModel(
    db: FirebaseFirestore
): ViewModel() {
    private val firebase = FirebaseUtilsImpl(db)

    var currentModel by mutableStateOf<KezModel>(KezModel())

    var cards = mutableListOf<KezModel>()
    var isDoneLoad by mutableStateOf(false)


    fun getCards() {
        firebase.getCards(
            object : FirebaseCallback {
                override fun onSuccess(models: List<KezModel>) {
                    cards = models.toMutableList()
                    isDoneLoad = true
                }

                override fun onError(msg: String) {

                }

            }
        )
    }


    init {
        getCards()
    }
}
