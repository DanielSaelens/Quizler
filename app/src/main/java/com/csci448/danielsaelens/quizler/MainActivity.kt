package com.csci448.danielsaelens.quizler

import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
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
import androidx.lifecycle.ViewModelProvider
import com.csci448.danielsaelens.quizler.data.QuestionRepo
import com.csci448.danielsaelens.quizler.ui.question.QuestionScreen
import com.csci448.danielsaelens.quizler.ui.theme.QuizlerTheme
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerIntent
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModel
import com.csci448.danielsaelens.quizler.ui.viewmodel.QuizlerViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var _viewModel: QuizlerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")
        enableEdgeToEdge()
        //val viewModel = QuizlerViewModel(QuestionRepo.questions)
        val factory = QuizlerViewModelFactory(savedInstanceState)
        _viewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]

        setContent {
            MainActivityContent(_viewModel)
        }

    }
    override fun onStart(){
        super.onStart()
        Log.d(LOG_TAG, "onStart() called")
    }


    override fun onResume(){
        super.onResume()
        Log.d(LOG_TAG,"onResume() called")
    }


    override fun onPause(){
        super.onPause()
        Log.d(LOG_TAG, "onPause() called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(LOG_TAG, "onStop() called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy() called")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.d(LOG_TAG, "onContentChanged() called")
    }

    override fun onPostCreate(savedInstanceState: Bundle?){
        super.onPostCreate(savedInstanceState)
        Log.d(LOG_TAG, "onPostCreate() called")

    }

    override fun onPostResume(){
        super.onPostResume()
        Log.d(LOG_TAG, "onPostResume() called")
    }

    override fun onAttachedToWindow(){
        super.onAttachedToWindow()
        Log.d(LOG_TAG, "onAttachedToWindow() called")
    }

    override fun onEnterAnimationComplete(){
        super.onEnterAnimationComplete()
        Log.d(LOG_TAG, "onEnterAnimationComplete() called")
    }

    override fun onDetachedFromWindow(){
        super.onDetachedFromWindow()
        Log.d(LOG_TAG, "onDetachedFromWindow() called")
    }






    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }


    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(LOG_TAG, "onSaveInstanceState() called")
        _viewModel.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
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
    MainActivityContent(QuizlerViewModel(QuestionRepo.questions, QuizlerViewModel.QuizlerState()))
}

