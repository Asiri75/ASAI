import androidx.compose.runtime.State
//Made by Baptiste 07/06/2023
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
    //Made by Baptiste 07/06/2023
    private val _uiState = MutableStateFlow(RecipeStateUI())
    val uiState: StateFlow<RecipeStateUI> = _uiState.asStateFlow()

    private val searchRecipesUseCase = SearchRecipesUseCase()

    //Used for enter ingredients in textInpu on RecipeScreen
    private val _textInput = mutableStateOf("")
    var textInput: String
        get() = _textInput.value
        set(value) {
            _textInput.value = value
        }

    //Used for display circleprogressindicator during request to api
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        resetScreen()
    }

    fun resetScreen() {
        _uiState.value = RecipeStateUI(currentScrambledWord = "Donne le plat que tu veux faire !")
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
//Made by Baptiste 07/06/2023