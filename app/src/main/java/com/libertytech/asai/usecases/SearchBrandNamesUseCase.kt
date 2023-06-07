package com.libertytech.asai.usecases

import com.aallam.openai.api.completion.TextCompletion
import com.libertytech.asai.repositories.OpenAiRepository


class SearchBrandNamesUseCase {

    companion object {
        const val ACTIVITY_NAME_REQUEST = "Donne moi 5 idées de noms d'entreprise à partir de ces infos :"
    }
    val openAiRepository = OpenAiRepository()

    suspend fun execute(brandNameDescription: String){
        openAiRepository.callChatGPT(
            "$ACTIVITY_NAME_REQUEST $brandNameDescription"
        )
    }

}
