package com.csci448.danielsaelens.quizler.ui.question.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.csci448.danielsaelens.quizler.R
import androidx.compose.ui.res.stringResource

@Composable
fun QuestionScoreText(score: Int){
    Text(text = stringResource(id = R.string.label_score_formatter, score))


}