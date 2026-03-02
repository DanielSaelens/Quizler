package com.csci448.danielsaelens.quizler.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.csci448.danielsaelens.quizler.ui.question.QuestionScreen
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.QuizlerIntent
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.QuestionIntent

// IScreenSpec defines a contract that every screen in the app must follow —
// each screen must declare its route (a URL-like identifier) and its composable content.
// The companion object uses Kotlin reflection to automatically discover all screens,
// so adding a new screen only requires creating a new object implementing IScreenSpec —
// the navigation system picks it up without manual registration.
// This serves as the foundation for the NavHost to know which screens exist,
// where to start, and how to route between them.
object QuestionScreenSpec : IScreenSpec {
    override val route = "question"

    @Composable
    override fun Content(
        modifier: Modifier,
        quizlerViewModel: QuizlerViewModel,
        navController: NavController
    ) {

        val (state, dispatcher, effect) = quizlerViewModel.questionContract.use()
        QuestionScreen(
            question = state.currentQuestion,
            onAnswered = { answerChoice ->
                dispatcher.invoke(QuestionIntent.Answer(answerChoice))
            },
            onPreviousQuestion = { dispatcher.invoke(QuestionIntent.Previous) },
            onNextQuestion = { dispatcher.invoke(QuestionIntent.Next) },
            score = state.score,
            answered = state.answeredCorrectly,
            onCheatButtonClick = { navController.navigate(CheatScreenSpec.route) }
        )
    }
}
