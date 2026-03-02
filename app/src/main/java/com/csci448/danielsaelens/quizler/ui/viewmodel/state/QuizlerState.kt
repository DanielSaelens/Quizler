package com.csci448.danielsaelens.quizler.ui.viewmodel.state

data class QuizlerState(
    val currentQuestionIndex: Int = 0,
    val score: Int = 0,
    val questionStatuses:  List<QuestionStatus> = emptyList()

)