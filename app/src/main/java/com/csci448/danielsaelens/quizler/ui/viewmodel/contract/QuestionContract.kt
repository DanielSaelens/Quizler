package com.csci448.danielsaelens.quizler.ui.viewmodel.contract
import androidx.compose.runtime.State
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel
import com.csci448.danielsaelens.quizler.ui.viewmodel.effect.QuizlerEffect
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.QuestionIntent
import com.csci448.danielsaelens.quizler.ui.viewmodel.state.QuestionState


class QuestionContract(
    override val state: State<QuestionState>,
    override val effect: State<QuizlerEffect?>,
    viewModel: QuizlerViewModel
) : IScreenContract<QuestionState, QuestionIntent, QuizlerEffect> {
    override val dispatcher: (QuestionIntent) -> Unit = { intent ->
        viewModel.handleIntent(intent)
    }
}