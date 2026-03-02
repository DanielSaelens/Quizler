package com.csci448.danielsaelens.quizler.ui.navigation.specs

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.csci448.danielsaelens.quizler.ui.cheat.CheatScreen
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel
import com.csci448.danielsaelens.quizler.ui.viewmodel.effect.CheatEffect
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.CheatIntent

object CheatScreenSpec : IScreenSpec {
override val route = "cheat"

@Composable
override fun Content(
    modifier: Modifier,
    quizlerViewModel: QuizlerViewModel,
    navController: NavController,

) {
    val (state, dispatcher, effect) = quizlerViewModel.cheatContract.use()

    val context = LocalContext.current
    LaunchedEffect(key1 = effect) {
        if(effect is CheatEffect.ShowCheatToast){
            Toast.makeText(context, "Cheaters never prosper. ", Toast.LENGTH_SHORT).show()
        }
    }

    val question = state.currentQuestion
    val answerResId = when(question.answer) {
        0 -> question.choice1Id
        1 -> question.choice2Id
        2 -> question.choice3Id
        3 -> question.choice4Id
        else -> question.choice1Id
    }

    CheatScreen(
        onCheatButtonClick = { dispatcher.invoke(CheatIntent.Cheat) },
        onGoBackButtonClick = { navController.popBackStack() },
        cheated = state.cheated,
        answer = stringResource(id = answerResId!!)
    )


    }
}

