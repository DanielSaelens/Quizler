package com.csci448.danielsaelens.quizler.ui.question.components

import android.util.Log
import android.widget.Button
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.danielsaelens.quizler.data.Question
import androidx.compose.ui.Modifier

private const val LOG_TAG = "448.QuestionButton"
// Stateless button composable that displays text and delegates click handling to the caller
@Composable
fun QuestionButton(buttonText: String, onButtonClick: () -> Unit, modifier: Modifier, enabled: Boolean = true,
                   colors: ButtonColors = ButtonDefaults.buttonColors()){
    Log.d(LOG_TAG, "Composing button $buttonText")
    // For actual clicking
    ElevatedButton(onClick = onButtonClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 2.dp,
            focusedElevation = 8.dp,
            disabledElevation = 0.dp
        )



        ) {
        // The text written on the button
        Text(text = buttonText)
    }
}


@Preview
@Composable
fun PreviewQuestionButtonNext(){
    // Calls the questions button displays next
    QuestionButton(buttonText = "Next", onButtonClick = {},
        modifier = Modifier)
}

@Preview
@Composable
fun PreviewQuestionButtonFalse(){
    QuestionButton(buttonText = "False", onButtonClick = { },
        modifier = Modifier)
}

@Composable
fun QuestionDisplay(){

}

@Preview
@Composable
fun PreviewQuestionButtonDisabled(){
    QuestionButton(buttonText = "Disabled", onButtonClick = {}, modifier = Modifier, enabled = false)
}
