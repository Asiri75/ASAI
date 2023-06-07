package com.libertytech.asai.usecases

import com.libertytech.asai.repositories.OpenAiRepository

class SearchRecipesUseCase {

    companion object {
        const val RECIPES_NAME_REQUEST = "Propose moi une recette avec ces ingredients "
    }
    private val openAiRepository:OpenAiRepository = OpenAiRepository()

    suspend fun execute(ingredients: String): String {
        val request = "$RECIPES_NAME_REQUEST $ingredients"
        val response = openAiRepository.callChatGPT(request)
        return response.choices.first().text
    }

}