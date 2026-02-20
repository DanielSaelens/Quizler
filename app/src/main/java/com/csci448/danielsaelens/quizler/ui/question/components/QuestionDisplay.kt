package com.csci448.danielsaelens.quizler.ui.question.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
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

private const val LOG_TAG = "448.QuestionDisplay"

@Composable
fun QuestionDisplay(question: Question, answered: Boolean? = null,  onAnswered: (Int) -> Unit ) {
    Log.d(LOG_TAG, "Question display $question")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
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
                buttonText = stringResource(R.string.label_false),
                onButtonClick = { onAnswered(0) },
                modifier = Modifier.weight(1f)
            )
            QuestionButton(
                buttonText = stringResource(id = R.string.label_true),
                onButtonClick = { onAnswered(1) },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
        if (answered != null) {
            Card(
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


@Composable
@Preview
fun PreviewQuestionDisplay(){
    QuestionDisplay(
        question = Question(R.string.question1, 1),
        onAnswered = {}
    )
}



