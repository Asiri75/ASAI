package com.libertytech.asai.usecases

import com.aallam.openai.api.completion.TextCompletion
import com.libertytech.asai.repositories.OpenAiRepository

class SalaryGeneratorUseCase {
    companion object{
        const val prepromtun = "Donne moi un nombre approximatif du salaire en euros en brut en France que je peux demander en tant que : "
        const val joiner = "et"
        const val prepromtdeux = "années d'expérience en te basant seulement sur ces points"
    }
    private val openAiRepository : OpenAiRepository = OpenAiRepository()
    suspend fun execute(job: String, xp: String): TextCompletion {
        try {
            return openAiRepository.callChatGPT("$prepromtun $job $joiner $xp $prepromtdeux")
        }catch (e: Exception){
            throw e
        }

    }
}