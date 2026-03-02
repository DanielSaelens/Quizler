package com.csci448.danielsaelens.quizler.ui.viewmodel.contract

import androidx.compose.runtime.State

sealed interface IScreenContract<STATE, INTENT, EFFECT> {
    val state: State<STATE>
    val dispatcher: (INTENT) -> Unit
    val effect: State<EFFECT?>
    data class StateDispatchEffect<STATE, INTENT, EFFECT>(
        val state: STATE,
        val dispatcher: (INTENT) -> Unit,
        val effect: EFFECT?
    )
    fun use() = StateDispatchEffect(
        state = state.value,
        dispatcher = dispatcher,
        effect = effect.value
    )
}