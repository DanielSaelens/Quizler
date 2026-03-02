package com.csci448.danielsaelens.quizler.ui.viewmodel.effect

import com.csci448.danielsaelens.quizler.ui.viewmodel.state.CheatState

sealed class CheatEffect : QuizlerEffect() {
    object ShowCheatToast : CheatEffect()
}