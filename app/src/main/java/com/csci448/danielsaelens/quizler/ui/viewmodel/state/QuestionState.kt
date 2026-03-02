package com.csci448.danielsaelens.quizler.ui.viewmodel.state

import com.csci448.danielsaelens.quizler.data.Question

data class QuestionState(
    val currentQuestion: Question,
    val score: Int = 0,
    val answeredCorrectly: Boolean? = null,
    val cheated: Boolean = false

)

