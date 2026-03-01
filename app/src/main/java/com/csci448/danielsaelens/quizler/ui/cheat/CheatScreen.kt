package com.csci448.danielsaelens.quizler.ui.cheat

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CheatScreen(
    onCheatButtonClick:() -> Unit,
    onGoBackButtonClick:() -> Unit
) {
    Column {
        Text("Are you sure you want to cheat?")
        Button(onClick = onCheatButtonClick) {
            Text("Cheat!")
        }
        Button(onClick = onGoBackButtonClick) {
            Text("Go Back!")
        }

    }
}

