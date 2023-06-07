import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.libertytech.asai.usecases.SearchBrandNamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BrandNameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BrandNameStateUI())
    val uiState: StateFlow<BrandNameStateUI> = _uiState.asStateFlow()

    private val searchBrandNamesUseCase = SearchBrandNamesUseCase()
    private val _textInput = mutableStateOf("")

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    var textInput: String
        get() = _textInput.value
        set(value) {
            _textInput.value = value
        }

    init {
        resetScreen()
    }

    fun resetScreen() {
        _uiState.value = BrandNameStateUI(currentScrambledWord = "text")
    }

    fun handleClick() {
        if (textInput.isNotEmpty()) {
            val description = textInput
            makeRequest(description)
        }
    }

    private fun makeRequest(description: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val response = searchBrandNamesUseCase.execute(description)

            withContext(Dispatchers.Main) {
                _uiState.value = BrandNameStateUI(currentScrambledWord = response)
                _isLoading.value = false
            }
        }
    }
}

data class BrandNameStateUI(
    val currentScrambledWord: String = ""
)