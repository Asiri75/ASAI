import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class LukasUiState(
        val currentScrambledWord: String = ""
)

class LukasViewModel: ViewModel() {
    private lateinit var currentWord: String
    private val _uiState = MutableStateFlow(LukasUiState())
    val uiState: StateFlow<LukasUiState> = _uiState.asStateFlow()

    fun resetWord() {
        _uiState.value = LukasUiState(currentScrambledWord = "Hello Lukas")
    }

    fun handleClick() {
       _uiState.value = LukasUiState(currentScrambledWord = "Yeah, you clicked the button")
    }

    init {
        resetWord()
    }
}