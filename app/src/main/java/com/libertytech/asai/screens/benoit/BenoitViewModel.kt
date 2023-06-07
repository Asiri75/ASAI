package com.libertytech.asai.screens.benoit

import androidx.lifecycle.ViewModel
import com.libertytech.asai.usecases.SalaryGeneratorUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BenoitViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(BenoitUIState())
    private var history = emptyArray<String>()
    val uiState: StateFlow<BenoitUIState> = _uiState.asStateFlow()
    private val salaryGeneratorUseCase: SalaryGeneratorUseCase = SalaryGeneratorUseCase()

    fun makeRequest(job: String, xp: String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = salaryGeneratorUseCase.execute(job, xp)
            history += "$job, $xp"
            withContext(Dispatchers.Main) {
                _uiState.value = BenoitUIState(response?.choices?.first()?.text.orEmpty(), history)
            }
        }
    }
    init {
        var content = BenoitUIState("Benoit Requillart")
        _uiState.value = content
    }
}