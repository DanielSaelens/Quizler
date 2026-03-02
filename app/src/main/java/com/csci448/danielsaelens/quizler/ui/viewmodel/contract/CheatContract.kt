package com.csci448.danielsaelens.quizler.ui.viewmodel.contract

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel
import com.csci448.danielsaelens.quizler.ui.viewmodel.effect.CheatEffect
import com.csci448.danielsaelens.quizler.ui.viewmodel.effect.QuizlerEffect
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.CheatIntent
import com.csci448.danielsaelens.quizler.ui.viewmodel.state.CheatState

class CheatContract(
    override val state: State<CheatState>,
    effect: State<QuizlerEffect?>,
    viewModel: QuizlerViewModel
) : IScreenContract<CheatState, CheatIntent, CheatEffect> {
    override val dispatcher: (CheatIntent) -> Unit = { intent ->
        viewModel.handleIntent(intent)
    }
    override val effect = derivedStateOf { effect.value as? CheatEffect }
}

