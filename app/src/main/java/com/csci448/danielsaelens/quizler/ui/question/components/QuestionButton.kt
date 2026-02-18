package com.csci448.danielsaelens.quizler.ui.question.components

import android.widget.Button
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.danielsaelens.quizler.data.Question


// Stateless button composable that displays text and delegates click handling to the caller
@Composable
fun QuestionButton(buttonText: String, onButtonClick: () -> Unit){
    // For actual clicking
    Button(onClick = onButtonClick) {
        // The text written on the button
        Text(text = buttonText)
    }
}


@Preview
@Composable
fun PreviewQuestionButtonNext(){
    // Calls the questions button displays next
    QuestionButton(buttonText = "Next", onButtonClick = {})
}

@Preview
@Composable
fun PreviewQuestionButtonFalse(){
    QuestionButton(buttonText = "False", onButtonClick = { })
}

@Composable
fun QuestionDisplay(){

}
