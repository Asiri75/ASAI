import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


data class BrandNameUiState(
    val searchFinalResult: String = "",
)

class BrandNameViewModel: ViewModel() {
    private val searchBrandNamesUseCase = SearchBrandNamesUseCase()

    private val _uiState = MutableStateFlow(BrandNameUiState())
    val uiState: StateFlow<BrandNameUiState> = _uiState.asStateFlow()

    fun makeRequest(brandDescription: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = searchBrandNamesUseCase.execute(brandDescription)

            withContext(Dispatchers.Main) {
                _uiState.value = BrandNameUiState(response.choices.first().text)
            }
        }
    }
}