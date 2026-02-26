package com.csci448.danielsaelens.quizler.ui.question.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.csci448.danielsaelens.quizler.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.danielsaelens.quizler.data.Question
import com.csci448.danielsaelens.quizler.ui.theme.Blue20
import com.csci448.danielsaelens.quizler.ui.theme.Gold60
import com.csci448.danielsaelens.quizler.ui.theme.Red40

private const val LOG_TAG = "448.QuestionDisplay"
// defaultButtonColors



@Composable
fun QuestionDisplay(
    question: Question,
    answered: Boolean? = null,
    onAnswered: (Int) -> Unit,
    orientation: Int = Configuration.ORIENTATION_PORTRAIT
) {
    Log.d(LOG_TAG, "Question display $question")

    val correctButtonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Gold60,
        disabledContentColor = Blue20
    )
    val incorrectButtonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Red40,
        disabledContentColor = Gold60
    )
    val defaultButtonColors = ButtonDefaults.buttonColors()

    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ElevatedCard(
                    modifier = Modifier.weight(1f).fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = question.questionTextId),
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuestionButton(
                        buttonText = stringResource(id = question.choice1Id),
                        onButtonClick = { onAnswered(0) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = answered == null,
                        colors = when {
                            answered == true && question.answer == 0 -> correctButtonColors
                            answered == false && question.answer == 0 -> incorrectButtonColors
                            else -> defaultButtonColors
                        }
                    )
                    QuestionButton(
                        buttonText = stringResource(id = question.choice2Id),
                        onButtonClick = { onAnswered(1) },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = answered == null,
                        colors = when {
                            answered == true && question.answer == 1 -> correctButtonColors
                            answered == false && question.answer == 1 -> incorrectButtonColors
                            else -> defaultButtonColors
                        }
                    )
                    if (question.choice3Id != null) {
                        QuestionButton(
                            buttonText = stringResource(id = question.choice3Id),
                            onButtonClick = { onAnswered(2) },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = answered == null,
                            colors = when {
                                answered == true && question.answer == 2 -> correctButtonColors
                                answered == false && question.answer == 2 -> incorrectButtonColors
                                else -> defaultButtonColors
                            }
                        )
                    }
                    if (question.choice4Id != null) {
                        QuestionButton(
                            buttonText = stringResource(id = question.choice4Id),
                            onButtonClick = { onAnswered(3) },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = answered == null,
                            colors = when {
                                answered == true && question.answer == 3 -> correctButtonColors
                                answered == false && question.answer == 3 -> incorrectButtonColors
                                else -> defaultButtonColors
                            }
                        )
                    }
                }
            }
        }

        else -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().height(445.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = question.questionTextId),
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    QuestionButton(
                        buttonText = stringResource(id = question.choice1Id),
                        onButtonClick = { onAnswered(0) },
                        modifier = Modifier.weight(1f),
                        enabled = answered == null,
                        colors = when {
                            answered == true && question.answer == 0 -> correctButtonColors
                            answered == false && question.answer == 0 -> incorrectButtonColors
                            else -> defaultButtonColors
                        }
                    )
                    QuestionButton(
                        buttonText = stringResource(id = question.choice2Id),
                        onButtonClick = { onAnswered(1) },
                        modifier = Modifier.weight(1f),
                        enabled = answered == null,
                        colors = when {
                            answered == true && question.answer == 1 -> correctButtonColors
                            answered == false && question.answer == 1 -> incorrectButtonColors
                            else -> defaultButtonColors
                        }
                    )
                }
                if (question.choice3Id != null) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        QuestionButton(
                            buttonText = stringResource(id = question.choice3Id),
                            onButtonClick = { onAnswered(2) },
                            modifier = Modifier.weight(1f),
                            enabled = answered == null,
                            colors = when {
                                answered == true && question.answer == 2 -> correctButtonColors
                                answered == false && question.answer == 2 -> incorrectButtonColors
                                else -> defaultButtonColors
                            }
                        )
                        if (question.choice4Id != null) {
                            QuestionButton(
                                buttonText = stringResource(id = question.choice4Id),
                                onButtonClick = { onAnswered(3) },
                                modifier = Modifier.weight(1f),
                                enabled = answered == null,
                                colors = when {
                                    answered == true && question.answer == 3 -> correctButtonColors
                                    answered == false && question.answer == 3 -> incorrectButtonColors
                                    else -> defaultButtonColors
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(64.dp))
                if (answered != null) {
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth().padding(16.dp, vertical = 8.dp)
                    ) {
                        if (answered) {
                            Text(
                                text = stringResource(id = R.string.message_correct),
                                modifier = Modifier.padding(36.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                        } else {
                            Text(
                                text = stringResource(id = R.string.message_wrong),
                                modifier = Modifier.padding(36.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}








@Composable
@Preview
fun PreviewQuestionDisplay(){
    QuestionDisplay(
        question = Question(R.string.question1, 1),
        onAnswered = {}
    )
}

@Composable
@Preview
fun PreviewQuestionDisplay2(){
    QuestionDisplay(
        question = Question(
            questionTextId = R.string.question6,
            answer = 2,
            choice1Id = R.string.q6_choice1,
            choice2Id = R.string.q6_choice2,
            choice3Id = R.string.q6_choice3,
            choice4Id = R.string.q6_choice4

        ),
        onAnswered = {}

    )
}


@Composable
@Preview
fun PreviewQuestionDisplayCorrect(){
    QuestionDisplay(
        question = Question(R.string.question1,1),
        answered = true,
        onAnswered = {}
    )
}




@Composable
@Preview
fun PreviewQuestionDisplayInCorrect(){
    QuestionDisplay(
        question = Question(R.string.question1, 1),
        answered = false,
        onAnswered = {}
    )
}
@Composable
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
fun PreviewQuestionDisplayLandscape() {
    QuestionDisplay(
        question = Question(
            questionTextId = R.string.question6,
            answer = 2,
            choice1Id = R.string.q6_choice1,
            choice2Id = R.string.q6_choice2,
            choice3Id = R.string.q6_choice3,
            choice4Id = R.string.q6_choice4
        ),
        onAnswered = {},
        orientation = Configuration.ORIENTATION_LANDSCAPE
    )
}
