package com.kez.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.kez.quiz.presentation.navigation.AppNavigation
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.presentation.vms.KezViewModelFactory
import com.kez.quiz.ui.theme.KezQuizTheme

class MainActivity : ComponentActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var kezViewModel: Lazy<KezViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()

        kezViewModel = viewModels(factoryProducer = { KezViewModelFactory(db) })

        enableEdgeToEdge()
        setContent {
            kezViewModel.value.getCards()
            KezQuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(kezViewModel.value)
                }
            }
        }
    }
}




