package com.csci448.danielsaelens.quizler.ui.question.components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.csci448.danielsaelens.quizler.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


private const val LOG_TAG = "448.QuestionScoreText"

@Composable
fun QuestionScoreText(score: Int){
    Log.d(LOG_TAG, "Composing Score $score" )
    Text(text = stringResource(id = R.string.label_score_formatter, score),
        modifier = Modifier.padding(bottom = 16.dp))


}

