package com.csci448.danielsaelens.quizler.ui.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.danielsaelens.quizler.data.QuestionRepo

class QuizlerViewModelFactory(private val bundle: Bundle?) : ViewModelProvider.NewInstanceFactory(){
private val initialState = QuizlerViewModel.createStateFromBundle(bundle)
    companion object{
        private const val LOG_TAG = "QuizlerViewModelFactory"
    }
    fun getViewModelClass() = QuizlerViewModel::class.java

    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        Log.d(LOG_TAG, "Creating $modelClass ")
        if(modelClass.isAssignableFrom(getViewModelClass())){
            return modelClass
                .getConstructor(
                    List::class.java,
                    QuizlerViewModel.QuizlerState::class.java
                )
                .newInstance(
                    QuestionRepo.questions,
                    initialState
                )
        }
            throw IllegalArgumentException("Unknown Viewmodel: $modelClass")
    }
}
