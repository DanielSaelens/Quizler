package com.csci448.danielsaelens.quizler.ui.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.csci448.danielsaelens.quizler.data.Question
import com.csci448.danielsaelens.quizler.data.QuestionRepo
import com.csci448.danielsaelens.quizler.data.QuestionRepo.questions

class QuizlerViewModel(private val questions: List<Question>,
    initialQuizlerState: QuizlerState = QuizlerState()
) {
    private val _quizlerState = mutableStateOf(initialQuizlerState)

    private val _questionState = mutableStateOf(

        QuestionState(
            currentQuestion = questions[initialQuizlerState.currentQuestionIndex]
        )

    )
    val questionState = derivedStateOf { _questionState.value}

    private fun handleQuestionIntent(intent: QuizlerIntent.QuestionIntent) = when(intent) {

        is QuizlerIntent.QuestionIntent.Previous ->{

            _quizlerState.value = _quizlerState.value.copy(
                currentQuestionIndex = (_quizlerState.value.currentQuestionIndex - 1 + questions.size) % questions.size
            )

            _questionState.value = _questionState.value.copy(
                currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                answeredCorrectly = null
                )
        }

        is QuizlerIntent.QuestionIntent.Next -> {

            _quizlerState.value = _quizlerState.value.copy(
                currentQuestionIndex = (_quizlerState.value.currentQuestionIndex + 1) % questions.size
            )

            _questionState.value = _questionState.value.copy(
                currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                answeredCorrectly = null
                )

        }

        is QuizlerIntent.QuestionIntent.Answer ->{
            val isCorrect = intent.answer == questions[_quizlerState.value.currentQuestionIndex].answer
            if(isCorrect){
                _quizlerState.value = _quizlerState.value.copy(
                    score = _quizlerState.value.score + 1
                )

            }
            _questionState.value = _questionState.value.copy(
                answeredCorrectly = isCorrect,
                score = _quizlerState.value.score
            )

        }

    }

    fun handleIntent(intent: QuizlerIntent) = when(intent) {
        is QuizlerIntent.QuestionIntent -> { handleQuestionIntent(intent) }
    }


}

data class QuizlerState(
    val currentQuestionIndex: Int = 0,
    val score: Int = 0
    )

data class QuestionState(
    val currentQuestion: Question,
    val score: Int = 0,
    val answeredCorrectly: Boolean? = null

    )


sealed class QuizlerIntent {
    sealed class QuestionIntent : QuizlerIntent() {
        object Previous : QuestionIntent()
        object Next : QuestionIntent()
        class Answer(val answer: Int) : QuestionIntent()
    }
}


