import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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


    fun makeRequest(companyDescription: String){
        CoroutineScope(Dispatchers.IO).launch {
            //Appel bloquant

            withContext(Dispatchers.Main) {
                //Modifier uiState
            }
        }
    }

    init {
        resetWord()
    }
}