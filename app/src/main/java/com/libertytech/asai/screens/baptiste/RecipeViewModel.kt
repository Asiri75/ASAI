package com.libertytech.asai.screens.baptiste
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class RecipeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        resetScreen()
    }
    fun resetScreen() {

        _uiState.value = GameUiState(currentScrambledWord = "ratata")
    }
    fun handleClick() {
        _uiState.value = GameUiState(currentScrambledWord = "titititi")
    }
}

data class GameUiState(
    val currentScrambledWord: String = ""
)




