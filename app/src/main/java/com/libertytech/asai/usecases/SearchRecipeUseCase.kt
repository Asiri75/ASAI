package com.libertytech.asai.usecases
//Made by Baptiste 07/06/2023
import com.libertytech.asai.repositories.OpenAiRepository

class SearchRecipesUseCase {
    private val openAiRepository:OpenAiRepository = OpenAiRepository()
    companion object {
        const val RECIPES_NAME_REQUEST = "Donne moi, sous forme de liste, les ingrédients pour la recette de :"
    }
    suspend fun execute(ingredients: String): String {
        return openAiRepository.callChatGPT("$RECIPES_NAME_REQUEST $ingredients ?").choices.first().text.replace("$RECIPES_NAME_REQUEST $ingredients ?", "")
    }
}