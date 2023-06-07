package com.libertytech.asai.screens.aymeric


import androidx.lifecycle.ViewModel
import com.libertytech.asai.usecases.SearchActivityNamesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



    // Game UI state
    data class ActivityUiState(
        val searchFinalResult: String = "",
    )

    class ActivityViewModel: ViewModel() {
        private val searchActivityNamesUseCase = SearchActivityNamesUseCase()

        private val _uiState = MutableStateFlow(ActivityUiState())
        val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()

        fun makeRequest(activityDescription: String) {
            CoroutineScope(Dispatchers.IO).launch {
                val response = searchActivityNamesUseCase.execute(activityDescription)

                withContext(Dispatchers.Main) {
                    _uiState.value = ActivityUiState(response.choices.first().text)
                }
            }
        }
    }