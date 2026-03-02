package com.csci448.danielsaelens.quizler.ui.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.ViewModel
import com.csci448.danielsaelens.quizler.data.Question
import com.csci448.danielsaelens.quizler.data.QuestionRepo
import com.csci448.danielsaelens.quizler.data.QuestionRepo.questions
import com.csci448.danielsaelens.quizler.ui.viewmodel.contract.CheatContract
import com.csci448.danielsaelens.quizler.ui.viewmodel.contract.QuestionContract
import com.csci448.danielsaelens.quizler.ui.viewmodel.effect.CheatEffect
import com.csci448.danielsaelens.quizler.ui.viewmodel.effect.QuizlerEffect
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.CheatIntent
import com.csci448.danielsaelens.quizler.ui.viewmodel.state.CheatState
import com.csci448.danielsaelens.quizler.ui.viewmodel.state.QuestionStatus
import com.csci448.danielsaelens.quizler.ui.viewmodel.state.QuestionState
import com.csci448.danielsaelens.quizler.ui.viewmodel.state.QuizlerState
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.QuestionIntent
import com.csci448.danielsaelens.quizler.ui.viewmodel.intent.QuizlerIntent




class QuizlerViewModel(private val questions: List<Question>,
    initialQuizlerState: QuizlerState
) : ViewModel() {
    private val _quizlerState = mutableStateOf(initialQuizlerState)
    private val _cheatState =
        mutableStateOf(CheatState(currentQuestion =
        questions[initialQuizlerState.currentQuestionIndex]))





    private val _effectState: MutableState<QuizlerEffect?> = mutableStateOf(null)
    val effectState = derivedStateOf { _effectState.value }



    private val _questionState = mutableStateOf(

        QuestionState(
            currentQuestion = questions[initialQuizlerState.currentQuestionIndex],
                    score = initialQuizlerState.score
        )

    )

    val questionContract = QuestionContract(
        state = _questionState,
        effect = _effectState,
        viewModel = this@QuizlerViewModel
    )
    val cheatContract = CheatContract(
        state = _cheatState,
        effect = _effectState,
        viewModel = this@QuizlerViewModel
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

    private fun handleQuestionIntent(intent: QuestionIntent) {
                Log.d(LOG_TAG, "handleQuestionIntent() called with $intent")
                    when (intent) {

            is QuestionIntent.Previous -> {

                _quizlerState.value = _quizlerState.value.copy(
                    currentQuestionIndex = (_quizlerState.value.currentQuestionIndex - 1 + questions.size) % questions.size
                )


                _questionState.value = _questionState.value.copy(
                    currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                    answeredCorrectly = when(_quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex]){
                        QuestionStatus.UNANSWERED -> null
                        QuestionStatus.ANSWERED_CORRECT -> true
                        QuestionStatus.ANSWERED_INCORRECT -> false
                        QuestionStatus.ANSWER_CHEATED_CORRECT -> true
                        QuestionStatus.ANSWER_CHEATED_INCORRECT -> false
                        QuestionStatus.CHEATED -> null
                    }

                )
                _cheatState.value = _cheatState.value.copy(
                    currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                    cheated = _quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex] == QuestionStatus.CHEATED ||
                            _quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex] == QuestionStatus.ANSWER_CHEATED_CORRECT ||
                            _quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex] == QuestionStatus.ANSWER_CHEATED_INCORRECT
                )
            }

            is QuestionIntent.Next -> {

                _quizlerState.value = _quizlerState.value.copy(
                    currentQuestionIndex = (_quizlerState.value.currentQuestionIndex + 1) % questions.size
                )


                _questionState.value = _questionState.value.copy(
                    currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                    answeredCorrectly = when(_quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex]){
                        QuestionStatus.UNANSWERED -> null
                        QuestionStatus.ANSWERED_CORRECT -> true
                        QuestionStatus.ANSWERED_INCORRECT -> false
                        QuestionStatus.ANSWER_CHEATED_CORRECT -> true
                        QuestionStatus.ANSWER_CHEATED_INCORRECT -> false
                        QuestionStatus.CHEATED -> null
                    }


                )
                _cheatState.value = _cheatState.value.copy(
                    currentQuestion = questions[_quizlerState.value.currentQuestionIndex],
                    cheated = _quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex] == QuestionStatus.CHEATED ||
                            _quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex] == QuestionStatus.ANSWER_CHEATED_CORRECT ||
                            _quizlerState.value.questionStatuses[_quizlerState.value.currentQuestionIndex] == QuestionStatus.ANSWER_CHEATED_INCORRECT
                )


            }

                        is QuestionIntent.Answer -> {
                            val currentIndex = _quizlerState.value.currentQuestionIndex
                            val isCorrect = intent.answer == questions[currentIndex].answer
                            val wasCheated = _quizlerState.value.questionStatuses[currentIndex] == QuestionStatus.CHEATED

                            // Only award a point if correct AND didn't cheat
                            if (isCorrect && !wasCheated) {
                                _quizlerState.value = _quizlerState.value.copy(
                                    score = _quizlerState.value.score + 1
                                )
                            }

                            _questionState.value = _questionState.value.copy(
                                answeredCorrectly = isCorrect,
                                score = _quizlerState.value.score
                            )

                            val updatedStatuses = _quizlerState.value.questionStatuses.toMutableList()
                            updatedStatuses[currentIndex] = when {
                                isCorrect && wasCheated -> QuestionStatus.ANSWER_CHEATED_CORRECT
                                !isCorrect && wasCheated -> QuestionStatus.ANSWER_CHEATED_INCORRECT
                                isCorrect -> QuestionStatus.ANSWERED_CORRECT
                                else -> QuestionStatus.ANSWERED_INCORRECT
                            }
                            _quizlerState.value = _quizlerState.value.copy(
                                questionStatuses = updatedStatuses
                            )
                        }

        }
    }
    private fun handleCheatIntent(intent: CheatIntent) {
        Log.d(LOG_TAG, "handleCheatIntent() called with $intent")
        when (intent) {
            is CheatIntent.Cheat -> {
                val currentIndex = _quizlerState.value.currentQuestionIndex
                if (_quizlerState.value.questionStatuses[currentIndex] == QuestionStatus.UNANSWERED) {
                    val updatedStatuses = _quizlerState.value.questionStatuses.toMutableList()
                    updatedStatuses[currentIndex] = QuestionStatus.CHEATED
                    _quizlerState.value = _quizlerState.value.copy(questionStatuses = updatedStatuses)
                }
                _questionState.value = _questionState.value.copy(cheated = true)
                _cheatState.value = _cheatState.value.copy(cheated = true)
                _effectState.value = CheatEffect.ShowCheatToast
            }
        }
    }


    fun handleIntent(intent: QuizlerIntent) {
        Log.d(LOG_TAG, "handleIntent() called with $intent")
        when (intent) {
            is QuestionIntent -> {
                handleQuestionIntent(intent)
            }
            is CheatIntent -> {
                handleCheatIntent(intent)
            }
        }
    }

    companion object {
        private const val LOG_TAG = "448.QuizlerViewModel"
        private const val KEY_INDEX = "currentQuestionIndex"
        private const val KEY_SCORE = "currentScore"

        private const val KEY_STATUSES = "questionStatuses"

        fun createStateFromBundle(inState: Bundle?): QuizlerState {
            Log.d(LOG_TAG, "createStateFromBundle() called with bundle: $inState")
            return inState?.let {
                val state = QuizlerState(
                    currentQuestionIndex = it.getInt(KEY_INDEX, QuizlerState().currentQuestionIndex),
                    score = it.getInt(KEY_SCORE, QuizlerState().score),
                    questionStatuses = it.getIntArray(KEY_STATUSES)?.map { ordinal -> QuestionStatus.entries[ordinal] }?: emptyList()

                )
                Log.d(LOG_TAG, "Restored state: index=${state.currentQuestionIndex}, score=${state.score}")
                state
            } ?: QuizlerState()
        }
    }


    fun saveInstanceState(outState: Bundle){
        Log.d(LOG_TAG, "saveInstanceState() called")
        outState.putInt(KEY_INDEX, _quizlerState.value.currentQuestionIndex)
        outState.putInt(KEY_SCORE,_quizlerState.value.score)
        outState.putIntArray(KEY_STATUSES, _quizlerState.value.questionStatuses.map { it.ordinal}.toIntArray())
    }


}









