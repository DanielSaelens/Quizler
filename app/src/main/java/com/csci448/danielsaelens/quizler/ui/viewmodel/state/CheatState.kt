package com.csci448.danielsaelens.quizler.ui.viewmodel.state

import com.csci448.danielsaelens.quizler.data.Question

data class CheatState(
    val currentQuestion: Question,
    val cheated: Boolean = false
)