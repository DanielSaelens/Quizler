package com.csci448.danielsaelens.quizler.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.csci448.danielsaelens.quizler.ui.navigation.specs.IScreenSpec
import com.csci448.danielsaelens.quizler.ui.navigation.specs.IScreenSpec.Companion.startDestination
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel

@Composable
fun QuizlerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    quizlerViewModel: QuizlerViewModel
) {
    NavHost(
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { screen ->
                if (screen != null) {
                    composable(route = screen.route) {
                        screen.Content(
                            modifier = modifier,
                            quizlerViewModel = quizlerViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}