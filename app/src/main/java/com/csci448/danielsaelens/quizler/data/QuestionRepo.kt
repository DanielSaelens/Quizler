package com.csci448.danielsaelens.quizler.data

import android.util.Log
import com.csci448.danielsaelens.quizler.R
private const val LOG_TAG = "448.QuestionRepo"
object QuestionRepo {

    // List of all quiz questions; each Question holds its text, correct answer, and answer choices 
    val questions = listOf(
        // True/False questions use default choice1Id (False) and choice2Id (True)
        Question(R.string.question1,0),
        Question(R.string.question2, answer = 1),
        Question(R.string.question3,1),
        Question(R.string.question4, answer = 0),
        Question(R.string.question5, answer = 1),
        // Multiple choice question with four explicit choices; answer is zero-based index
        Question(questionTextId = R.string.question6, answer = 2,
            choice1Id = R.string.q6_choice1,
            choice2Id = R.string.q6_choice2,
            choice3Id = R.string.q6_choice3,
            choice4Id = R.string.q6_choice4
            )



    )



    init {
        Log.d(LOG_TAG, "QuestionRepo initialized")
    }

}