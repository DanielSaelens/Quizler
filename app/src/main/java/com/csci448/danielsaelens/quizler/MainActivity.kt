package com.csci448.danielsaelens.quizler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.danielsaelens.quizler.data.QuestionRepo
import com.csci448.danielsaelens.quizler.ui.question.QuestionScreen
import com.csci448.danielsaelens.quizler.ui.theme.QuizlerTheme
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerIntent
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = QuizlerViewModel(QuestionRepo.questions)
        setContent {
            MainActivityContent(viewModel)

        }
    }
}


@Composable
private fun MainActivityContent(viewModel: QuizlerViewModel){
    val questionState = viewModel.questionState.value
    QuizlerTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            QuestionScreen(
                question = questionState.currentQuestion,
                onAnswered = { answerChoice ->
                    viewModel.handleIntent(QuizlerIntent.QuestionIntent.Answer(answerChoice))
                },
                onPreviousQuestion = { viewModel.handleIntent(QuizlerIntent.QuestionIntent.Previous)},
                onNextQuestion = {viewModel.handleIntent(QuizlerIntent.QuestionIntent.Next)},
                score = questionState.score,
                answered = questionState.answeredCorrectly
            )
        }
    }

}

@Preview
@Composable
fun PreviewMainActivityContent() {
    MainActivityContent(QuizlerViewModel(QuestionRepo.questions))
}