package com.csci448.danielsaelens.quizler.ui.viewmodel.intent

sealed class QuestionIntent : QuizlerIntent() {
    object Previous : QuestionIntent()
    object Next : QuestionIntent()
    class Answer(val answer: Int) : QuestionIntent()
}