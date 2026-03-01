package com.csci448.danielsaelens.quizler.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.danielsaelens.quizler.ui.cheat.CheatScreen
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel

object CheatScreenSpec : IScreenSpec {
override val route = "cheat"

@Composable
override fun Content(
    modifier: Modifier,
    quizlerViewModel: QuizlerViewModel,
    navController: NavController,

) {
    CheatScreen(
        onCheatButtonClick = { },
        onGoBackButtonClick = { navController.popBackStack() }

    )


    }
}

