package com.kez.quiz.presentation.vms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


/**
 * НА ЭТО ДАЖЕ НЕ СМОТРИ, ОНО ТЕБЕ НЕ НАДО, НО ПРОГРАММЕ НАДО!
 */
class KezViewModelFactory(private val db: FirebaseFirestore): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KezViewModel(db = db) as T
    }
}