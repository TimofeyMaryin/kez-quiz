package com.kez.quiz.domain

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.kez.quiz.data.FirebaseCallback
import com.kez.quiz.data.KezModel
import com.kez.quiz.data.QuizModel


/**
 * Ну тут на самом деле сложновато будет объяснить и я не вижу смысла это делать
 * на таком уровне твой подготовке, поэтому просто скпни.
 *
 * Если вкратце, то мы просто работает с интерфейсами и API Firebase, никакой самодеятельности
 */
interface FirebaseUtils {

    fun getCards(callback: FirebaseCallback,)

}


class FirebaseUtilsImpl(private val db: FirebaseFirestore): FirebaseUtils {
    override fun getCards(
        callback: FirebaseCallback,
    ) {
        db.collection("cards")
            .get()
            .addOnSuccessListener { cardDocuments ->
                val cardList = mutableListOf<KezModel>()

                for (cardDocument in cardDocuments) {
                    val theme = cardDocument.getString("theme") ?: "error"
                    val quizList = mutableListOf<QuizModel>()

                    // Получаем коллекцию questions для текущего документа card
                    cardDocument.reference.collection("questions")
                        .get()
                        .addOnSuccessListener { questionDocuments ->
                            for (questionDocument in questionDocuments) {
                                val question = questionDocument.getString("question") ?: "error"
                                val answers = questionDocument.get("answers") as? List<String> ?: emptyList()
                                val currentIndex = (questionDocument.getLong("currentIndex") ?: 0L).toInt()

                                val quiz = QuizModel(
                                    question = question,
                                    answers = answers,
                                    currentIndex = currentIndex
                                )
                                quizList.add(quiz)
                            }

                            // После получения всех вопросов добавляем карточку
                            val card = KezModel(
                                theme = theme,
                                questions = quizList
                            )
                            cardList.add(card)

                            // Вызываем callback после добавления всех карточек
                            if (cardList.size == cardDocuments.size()) {
                                callback.onSuccess(models = cardList)
                            }
                        }
                        .addOnFailureListener {
                            callback.onError("Ошибка при получении вопросов")
                        }
                }
            }
            .addOnFailureListener {
                callback.onError("On Failed")
            }
    }

}