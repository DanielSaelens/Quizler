package com.csci448.danielsaelens.quizler.ui.navigation.specs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel

sealed interface IScreenSpec {

    val route: String

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        quizlerViewModel: QuizlerViewModel,
        navController: NavController
    )

    companion object {
        val allScreens = IScreenSpec::class.sealedSubclasses.map { it.objectInstance }
        val root = "quizler"
        val startDestination = QuestionScreenSpec.route
        //val startDestination = CheatScreenSpec.route
    }


}

