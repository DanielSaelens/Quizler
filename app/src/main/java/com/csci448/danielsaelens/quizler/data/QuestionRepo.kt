package com.csci448.danielsaelens.quizler.data

import android.util.Log
import com.csci448.danielsaelens.quizler.R
private const val LOG_TAG = "448.QuestionRepo"
object QuestionRepo {

    val questions = listOf(
        Question(R.string.question1,0),
        Question(R.string.question2, answer = 1),
        Question(R.string.question3,1),
        Question(R.string.question4, answer = 0),
        Question(R.string.question5, answer = 1)

    )
    init {
        Log.d(LOG_TAG, "QuestionRepo initialized")
    }

}