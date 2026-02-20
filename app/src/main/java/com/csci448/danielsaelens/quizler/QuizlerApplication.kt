package com.csci448.danielsaelens.quizler

import android.app.Application
import android.util.Log

class QuizlerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG," onCreate() called" )
    }
    companion object{
        private const val LOG_TAG = "448.QuizlerApplication"
    }

}