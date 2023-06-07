package com.libertytech.asai.screens.aymeric

import MariusUiState
import androidx.lifecycle.ViewModel
import com.libertytech.asai.usecases.SearchActivityNamesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ActivityViewModel : ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow<String>("")
    val uiState: StateFlow<String> = _uiState.asStateFlow()
    val searchActivityNamesUseCase = SearchActivityNamesUseCase()

    fun handleClick() {
        makeRequest("Test")
    }


    fun makeRequest(activityDescription: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = searchActivityNamesUseCase.execute(activityDescription)

            withContext(Dispatchers.Main) {
                _uiState.value = ActivityUiState(response.choices.first().text).toString()
            }
        }
    }
    init{
        _uiState.value = "Paris"
    }

}


