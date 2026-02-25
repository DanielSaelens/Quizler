package com.csci448.danielsaelens.quizler.ui.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.ViewModel
import com.csci448.danielsaelens.quizler.data.Question
import com.csci448.danielsaelens.quizler.data.QuestionRepo
import com.csci448.danielsaelens.quizler.data.QuestionRepo.questions

class QuizlerViewModel(private val questions: List<Question>,
    initialQuizlerState: QuizlerState
) : ViewModel() {
    private val _quizlerState = mutableStateOf(initialQuizlerState)

    private val _questionState = mutableStateOf(

        QuestionState(
            currentQuestion = questions[initialQuizlerState.currentQuestionIndex],
                    score = initialQuizlerState.score
        )

    )
    val questionState = derivedStateOf { _questionState.value }

    init {
        if( _quizlerState.value.questionStatuses.isEmpty() ) {
            _quizlerState.value = _quizlerState.value.copy(
                questionStatuses = questions.map { QuestionStatus.UNANSWERED }
            )
        }
        Log.d(LOG_TAG, "viewModel initialized!")
    }




    override fun onCleared(){
        super.onCleared()
        Log.d(LOG_TAG, "onCleared() called")
    }

    private fun handleQuestionIntent(intent: QuizlerIntent.QuestionIntent) {
                Log.d(LOG_TAG, "handleQuestionIntent() called with $intent")
                    when (intent) {

            is QuizlerIntent.QuestionIntent.Previous -> {

                _quizlerState.value = _quizlerState.value.copy(
                    currentQuestionIndex = (_quizlerState.value.currentQuestionIndex - 1 + questions.size) % questions.size
                )

                _questionState.value = _questionState.value.copy(
                    currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                    answeredCorrectly = when(_quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex]){
                        QuestionStatus.UNANSWERED -> null
                        QuestionStatus.ANSWERED_CORRECT -> true
                        QuestionStatus.ANSWERED_INCORRECT -> false
                    }

                )
            }

            is QuizlerIntent.QuestionIntent.Next -> {

                _quizlerState.value = _quizlerState.value.copy(
                    currentQuestionIndex = (_quizlerState.value.currentQuestionIndex + 1) % questions.size
                )

                _questionState.value = _questionState.value.copy(
                    currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                    answeredCorrectly = when(_quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex]){
                        QuestionStatus.UNANSWERED -> null
                        QuestionStatus.ANSWERED_CORRECT -> true
                        QuestionStatus.ANSWERED_INCORRECT -> false
                    }
                )

            }

            is QuizlerIntent.QuestionIntent.Answer -> {
                val isCorrect =
                    intent.answer == questions[_quizlerState.value.currentQuestionIndex].answer
                if (isCorrect) {
                    _quizlerState.value = _quizlerState.value.copy(
                        score = _quizlerState.value.score + 1
                    )

                }
                _questionState.value = _questionState.value.copy(
                    answeredCorrectly = isCorrect,
                    score = _quizlerState.value.score

                )
                // Here
                val updatedStatuses = _quizlerState.value.questionStatuses.toMutableList()
                val currentIndex = _quizlerState.value.currentQuestionIndex
                if(isCorrect){
                    updatedStatuses[currentIndex] = QuestionStatus.ANSWERED_CORRECT
                } else{
                    updatedStatuses[currentIndex] = QuestionStatus.ANSWERED_INCORRECT
                }
                _quizlerState.value = _quizlerState.value.copy(
                    questionStatuses = updatedStatuses
                )

            }

        }
    }


    fun handleIntent(intent: QuizlerIntent) {
        Log.d(LOG_TAG, "handleIntent() called with $intent")
        when (intent) {
            is QuizlerIntent.QuestionIntent -> {
                handleQuestionIntent(intent)
            }
        }
    }

    companion object {
        private const val LOG_TAG = "448.QuizlerViewModel"
        private const val KEY_INDEX = "currentQuestionIndex"
        private const val KEY_SCORE = "currentScore"

        fun createStateFromBundle(inState: Bundle?): QuizlerState {
            Log.d(LOG_TAG, "createStateFromBundle() called with bundle: $inState")
            return inState?.let {
                val state = QuizlerState(
                    currentQuestionIndex = it.getInt(KEY_INDEX, QuizlerState().currentQuestionIndex),
                    score = it.getInt(KEY_SCORE, QuizlerState().score)
                )
                Log.d(LOG_TAG, "Restored state: index=${state.currentQuestionIndex}, score=${state.score}")
                state
            } ?: QuizlerState()
        }
    }
    data class QuizlerState(
        val currentQuestionIndex: Int = 0,
        val score: Int = 0,
        val questionStatuses:  List<QuestionStatus> = emptyList()

    )

    fun saveInstanceState(outState: Bundle){
        Log.d(LOG_TAG, "saveInstanceState() called")
        outState.putInt(KEY_INDEX, _quizlerState.value.currentQuestionIndex)
        outState.putInt(KEY_SCORE,_quizlerState.value.score)

    }


}


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




