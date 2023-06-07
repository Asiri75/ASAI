package com.libertytech.asai.repositories

import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.completion.TextCompletion
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI

class OpenAiRepository {

    private val openAI = OpenAI("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")

    suspend fun callChatGPT(prompt: String): TextCompletion {
        return openAI.completion(
            CompletionRequest(
                model = ModelId("text-curie-001"),
                prompt = prompt,
                echo = true,
                maxTokens = 300
            )
        )
    }

}