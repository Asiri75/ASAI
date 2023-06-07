import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


data class LukasUiState(
    val searchFinalResult: String = "",
)

class LukasViewModel: ViewModel() {
    private val lukasRoadTripGeneratorUseCase = LukasRoadTripGeneratorUseCase()

    private val _uiState = MutableStateFlow(LukasUiState())
    val uiState: StateFlow<LukasUiState> = _uiState.asStateFlow()

    fun makeRequest(roadTripDescription: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = lukasRoadTripGeneratorUseCase.execute(roadTripDescription)

            withContext(Dispatchers.Main) {
                _uiState.value = LukasUiState(response.choices.first().text)
            }
        }
    }
}