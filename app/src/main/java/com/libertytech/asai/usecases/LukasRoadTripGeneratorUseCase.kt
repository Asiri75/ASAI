import com.libertytech.asai.repositories.OpenAiRepository

class LukasRoadTripGeneratorUseCase {
    private val openAiRepository : OpenAiRepository = OpenAiRepository()

    companion object{
        const val ROADTRIP_REQUEST_START = "Pourriez-vous me donner une liste de 5 choses incontournables à faire et à voir en "
    }

    suspend fun execute(prompt: String): String {
        return openAiRepository.callChatGPT("$ROADTRIP_REQUEST_START $prompt ?").choices.first().text.replace("$ROADTRIP_REQUEST_START $prompt ?", "")
    }
}