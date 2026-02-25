package com.csci448.danielsaelens.quizler.data

import androidx.annotation.StringRes
import com.csci448.danielsaelens.quizler.R

data class Question(
    // @StringRes indicates this Int parameter must be a string resource ID (R.string.*),
    // allowing the compiler and lint to catch invalid values at compile time.
    @param:StringRes val questionTextId: Int,
    val answer: Int,
    @param:StringRes val choice1Id: Int = R.string.label_false,
    @param:StringRes val choice2Id: Int = R.string.label_true,
    @param:StringRes val choice3Id: Int? = null,
    @param:StringRes val choice4Id: Int? = null

)
