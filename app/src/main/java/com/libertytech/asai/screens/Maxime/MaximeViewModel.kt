package com.libertytech.asai.screens.Maxime
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow





class MaximeViewModel : ViewModel() {
    // Movie UI state
    private val _uiState = MutableStateFlow<String>("")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    init{
        _uiState.value = "Paris"
    }
}
