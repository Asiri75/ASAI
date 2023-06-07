package com.libertytech.asai.screens.aymeric

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ActivityViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(ActivityUiState())
    // Backing property to avoid state updates from other classes
    val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()
    init {
        var title = ActivityUiState("test")
        _uiState.value = title
    }


}


