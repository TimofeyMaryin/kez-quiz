package com.kez.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.google.firebase.firestore.FirebaseFirestore
import com.kez.quiz.presentation.navigation.AppNavigation
import com.kez.quiz.presentation.vms.KezViewModel
import com.kez.quiz.presentation.vms.KezViewModelFactory
import com.kez.quiz.ui.theme.KezQuizTheme


/**
 * Общий принцип работы приложения:
 *  у нас есть база данных Firebase. В ней хранятся все карточки для нашего приложения.
 *  Модель представления этих карточек мы описали при помощи data class KezModel()
 *
 *  Как мы получаем данные из Firebase:
 *  У нас есть класс FirebaseUtilsImpl(private val db: FirebaseFirestore) в котором реализована
 *  логика получения данных из Firebase
 *
 *  db - это переменная конструктора класса, которая принимает тип данных FirebaseFirestore, который
 *  непосредственно взаимодействует с Firebase. Данную переменную мы инициализировали в классе MainActivity
 *
 *  Чтобы взаимодействовать с классом FirebaseUtilsImpl() мы использовали архитектурный паттерн MVVM (Model-View-ViewModel),
 *  где мы уже создвали функцию getCards(), которая получает данные и присваивает их переменной cards.
 *
 *  На этом наша работа с подключечнием и взаимодействием с Firebase законченно! 😊
 *
 *  Далее мы создаем интерфейс (UI) для приложение. Вся разработка интерфейса проводиться в модуле "presentation"
 *  Чтоб получить больше информации переходи в модуль presentation и тыкай по разным файлам.
 *
 *
 * Если хочешь самостоятельно создавать UI, то вот некоторые минимально необходимые Composable фкнкции:
 *
 * Layout (расположение обьектов на сцене):
 * - Column()
 * - Row()
 * - Box()
 *
 * UI компоненты
 * - Box() PS: используя параметр modifier:
 * - Image()
 * - Button()
 * - Card() - со звездочкой (это не сложность определяет, а скорее приоритетность: можно в последнюю очередь)
 * - Icon()
 * - Text()
 * - IconButton()
 *
 * Дополнительно:
 * посмотри параметр Modifier. Он нужен для изменения внешнего вида всех Composable функций
 *
 *
 * Если хочешь добавить картинку вот правила:
 * - храняться в модуле res->drawable
 * - тип картинки .png/.jpg
 * - наименование переменной: name_of_image.png
 */
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
                    innerPadding
                }
            }
        }
    }
}




