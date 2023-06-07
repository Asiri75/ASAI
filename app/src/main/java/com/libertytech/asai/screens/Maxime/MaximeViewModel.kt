package com.libertytech.asai.screens.Maxime
import androidx.lifecycle.ViewModel
import com.libertytech.asai.screens.maxime.MovieUIState
import com.libertytech.asai.usecases.MovieGeneratorUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class MovieUiState(
    val finalResult: String = "",
)


class MaximeViewModel : ViewModel() {
    // Movie UI state
    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState: StateFlow<MovieUIState> = _uiState.asStateFlow()
    val movieGeneratorUseCase = MovieGeneratorUseCase()


    fun handleClick(){
        makeRequest("Le nom de l'acteur est Leonardo di Caprio")
    }

    fun makeRequest(movieDescription:String){
        CoroutineScope(Dispatchers.IO).launch{
            val response = movieGeneratorUseCase.execute(movieDescription)

            withContext(Dispatchers.Main) {
                _uiState.value = MovieUIState(response.choices.first().text)
            }
        }
    }
    init{
        var title = MovieUIState("Maxime Rouget")
        _uiState.value = title
    }
}
