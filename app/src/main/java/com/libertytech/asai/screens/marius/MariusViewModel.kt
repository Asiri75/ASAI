import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MariusUiState(
        val currentScrambledWord: String = ""
)

class MariusViewModel: ViewModel() {
    private lateinit var currentWord: String
    private val _uiState = MutableStateFlow(MariusUiState())
    val uiState: StateFlow<MariusUiState> = _uiState.asStateFlow()

    fun resetWord() {
        _uiState.value = MariusUiState(currentScrambledWord = "Hello Marius")
    }

    fun handleClick() {
        _uiState.value = MariusUiState(currentScrambledWord = "Tu m'as touché !")
    }

    init {
        resetWord()
    }
}