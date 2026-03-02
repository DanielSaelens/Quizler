package com.csci448.danielsaelens.quizler.ui.viewmodel.intent

sealed class CheatIntent : QuizlerIntent() {
    object Cheat: CheatIntent()
}