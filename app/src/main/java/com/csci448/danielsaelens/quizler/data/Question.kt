package com.csci448.danielsaelens.quizler.data

import androidx.annotation.StringRes

data class Question(
    @param:StringRes val questionTextId: Int,
    val answer: Int
)
