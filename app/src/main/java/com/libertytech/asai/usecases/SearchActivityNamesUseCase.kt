package com.libertytech.asai.usecases

import com.aallam.openai.api.completion.TextCompletion
import com.libertytech.asai.repositories.OpenAiRepository


class SearchActivityNamesUseCase {

    private val openAiRepository : OpenAiRepository = OpenAiRepository()

    companion object{
        const val ACTIVITY_REQUEST = "Donne moi une liste de 5 activités à : "
    }

    suspend fun execute(prompt: String): TextCompletion {
        return openAiRepository.callChatGPT("$ACTIVITY_REQUEST $prompt.")
    }
}

