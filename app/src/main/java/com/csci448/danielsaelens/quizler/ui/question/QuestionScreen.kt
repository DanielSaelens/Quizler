package com.csci448.danielsaelens.quizler.ui.question

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.danielsaelens.quizler.R
import com.csci448.danielsaelens.quizler.data.Question
import com.csci448.danielsaelens.quizler.ui.question.components.QuestionButton
import com.csci448.danielsaelens.quizler.ui.question.components.QuestionDisplay
import com.csci448.danielsaelens.quizler.ui.question.components.QuestionScoreText

@Composable
fun QuestionScreen(question: Question,
                   onAnswered: (Int) -> Unit,
                   onPreviousQuestion: () -> Unit,
                   onNextQuestion: () -> Unit,
                   score: Int,
                   answered: Boolean?
) {


//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    )
//

    
    Column {

        QuestionScoreText(score)
        QuestionDisplay(question = question, onAnswered = onAnswered, answered = answered)
        Row {
            QuestionButton(buttonText = stringResource(id = R.string.label_previous), onButtonClick = onPreviousQuestion)
            QuestionButton(buttonText = stringResource(id = R.string.label_next), onButtonClick = onNextQuestion)

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
        answered = null

    )

}




