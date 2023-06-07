package com.libertytech.asai.screens.benoit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BenoitViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(BenoitUIState())

    val uiState: StateFlow<BenoitUIState> = _uiState.asStateFlow()

    init {
        var title = BenoitUIState("Benoit Requillart")
        _uiState.value = title
    }
}