package com.libertytech.asai.usecases

import com.aallam.openai.api.completion.TextCompletion
import com.libertytech.asai.repositories.OpenAiRepository


class SearchActivityNamesUseCase {

    companion object {
        const val ACTIVITY_NAME_REQUEST = "Donne moi 4 activité à faire dans la ville suivante :"
    }
val openAiRepository = OpenAiRepository()

    suspend fun execute(activityDescription: String){
        openAiRepository.callChatGPT(
            "$ACTIVITY_NAME_REQUEST $activityDescription"
        )
    }

}

