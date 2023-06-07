import com.aallam.openai.api.completion.TextCompletion
import com.libertytech.asai.repositories.OpenAiRepository

class LukasRoadTripGeneratorUseCase {
    private val openAiRepository : OpenAiRepository = OpenAiRepository()

    companion object{
        const val ROADTRIP_REQUEST = "Donne moi une liste de 5 destinations à : "
    }

    suspend fun execute(prompt: String): TextCompletion {
        return openAiRepository.callChatGPT("$ROADTRIP_REQUEST $prompt.")
    }
}