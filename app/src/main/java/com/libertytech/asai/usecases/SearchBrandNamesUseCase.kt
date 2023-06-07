import com.aallam.openai.api.completion.TextCompletion
import com.libertytech.asai.repositories.OpenAiRepository

class SearchBrandNamesUseCase {
    private val openAiRepository : OpenAiRepository = OpenAiRepository()

    companion object{
        const val BRAND_NAME_REQUEST = "Donne moi 5 idées de noms originaux d'entreprise à partir de ces informations : "
    }

    suspend fun execute(prompt: String): TextCompletion {
        return openAiRepository.callChatGPT("$BRAND_NAME_REQUEST $prompt.")
    }
}