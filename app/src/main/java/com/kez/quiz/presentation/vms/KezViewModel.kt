package com.kez.quiz.presentation.vms

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.kez.quiz.data.FirebaseCallback
import com.kez.quiz.data.KezModel
import com.kez.quiz.domain.FirebaseUtilsImpl

class KezViewModel(
    db: FirebaseFirestore
): ViewModel() {
    val firebase = FirebaseUtilsImpl(db)

    fun getCards() {
        firebase.getCards(
            object : FirebaseCallback {
                override fun onSuccess(models: List<KezModel>) {
                    models.forEach {
                        Log.e("TAG", "onSuccess: $it", )
                    }
                }

                override fun onError(msg: String) {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}
