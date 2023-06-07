import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.libertytech.asai.usecases.SearchRecipesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeStateUI())
    val uiState: StateFlow<RecipeStateUI> = _uiState.asStateFlow()

    private val searchRecipesUseCase = SearchRecipesUseCase()
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
        _uiState.value = RecipeStateUI(currentScrambledWord = "ratata")
    }

    fun handleClick() {
        if (textInput.isNotEmpty()) {
            val ingredients = textInput
            makeRequest(ingredients)
        }
    }

    private fun makeRequest(ingredients: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val response = searchRecipesUseCase.execute(ingredients)

            withContext(Dispatchers.Main) {
                _uiState.value = RecipeStateUI(currentScrambledWord = response)
                _isLoading.value = false
            }
        }
    }
}

data class RecipeStateUI(
    val currentScrambledWord: String = ""
)
