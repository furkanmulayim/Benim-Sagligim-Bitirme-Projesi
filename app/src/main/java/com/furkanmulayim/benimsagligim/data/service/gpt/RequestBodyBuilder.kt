package com.furkanmulayim.benimsagligim.data.service.gpt

class RequestBodyBuilder {
    fun build(gptQuery: String): String {
        return """
        {
            "prompt": "You are a helpful assistant.\\nUser: $gptQuery",
            "max_tokens": 35,
            "temperature": 0.2,
            "n": 1
        }
        """.trimIndent()
    }
}