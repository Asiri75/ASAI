package com.libertytech.asai.usecases

import com.aallam.openai.api.completion.TextCompletion
import com.libertytech.asai.repositories.OpenAiRepository


class MovieGeneratorUseCase {

    private val openAiRepository : OpenAiRepository = OpenAiRepository()

    companion object{
        const val MOVIE_REQUEST = "Donne moi une liste de 5 films à : "
    }

    suspend fun execute(prompt: String): TextCompletion {
        return openAiRepository.callChatGPT("$MOVIE_REQUEST $prompt.")
    }

}
