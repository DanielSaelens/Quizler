package com.csci448.danielsaelens.quizler.ui.question
import android.util.Log
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.danielsaelens.quizler.R
import com.csci448.danielsaelens.quizler.data.Question
import com.csci448.danielsaelens.quizler.ui.question.components.QuestionButton
import com.csci448.danielsaelens.quizler.ui.question.components.QuestionDisplay
import com.csci448.danielsaelens.quizler.ui.question.components.QuestionScoreText

private const val LOG_TAG = "448.QuestionScreen"




@Composable
// Container that holds the question logic together
fun QuestionScreen(question: Question,
                   onAnswered: (Int) -> Unit,
                   onPreviousQuestion: () -> Unit,
                   onNextQuestion: () -> Unit,
                   score: Int,
                   answered: Boolean?,
                   onCheatButtonClick: () -> Unit
) {
    val orientation = LocalConfiguration.current.orientation
    Log.d(LOG_TAG, "Composing QuestionScreen")




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onCheatButtonClick) {
                Text("Cheat!")
            }
            QuestionScoreText(score)
        }
            QuestionDisplay(
                question = question,
                onAnswered = onAnswered,
                answered = answered,
                orientation = orientation
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)

            ) {
                QuestionButton(
                    buttonText = stringResource(id = R.string.label_previous),
                    onButtonClick = onPreviousQuestion,
                    modifier = Modifier.weight(1f)
                )
                QuestionButton(
                    buttonText = stringResource(id = R.string.label_next),
                    onButtonClick = onNextQuestion,
                    modifier = Modifier.weight(1f)
                )

            }
        }

    }


@Composable
@Preview
fun PreviewQuestionScreen(){
    QuestionScreen(
        question = Question(R.string.question1, 1),
        onAnswered = {},
        onPreviousQuestion = {},
        onNextQuestion = {},
        score = 0,
        answered = null,
        onCheatButtonClick = {}

    )

}
@Composable
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
fun PreviewQuestionScreenLandscape() {
    QuestionScreen(
        question = Question(
            questionTextId = R.string.question6,
            answer = 2,
            choice1Id = R.string.q6_choice1,
            choice2Id = R.string.q6_choice2,
            choice3Id = R.string.q6_choice3,
            choice4Id = R.string.q6_choice4
        ),
        onAnswered = {},
        onPreviousQuestion = {},
        onNextQuestion = {},
        score = 0,
        answered = null,
        onCheatButtonClick = {}
    )
}



