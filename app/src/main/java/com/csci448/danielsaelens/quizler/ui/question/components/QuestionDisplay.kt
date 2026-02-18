package com.csci448.danielsaelens.quizler.ui.question.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.csci448.danielsaelens.quizler.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.danielsaelens.quizler.data.Question

@Composable
fun QuestionDisplay(question: Question, answered: Boolean? = null,  onAnswered: (Int) -> Unit ){

        Column() {

            Card{ Text(text = stringResource(id = question.questionTextId))
            }

            Row{
                QuestionButton(buttonText = stringResource(R.string.label_false), onButtonClick =  {onAnswered(0) })
                QuestionButton(buttonText = stringResource(id = R.string.label_true), onButtonClick = {onAnswered(1)})

            }
                if(answered != null){
                    Text(text = stringResource(id = R.string.message_correct))
                } else{
                    Text(text = stringResource(id = R.string.message_wrong))
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



