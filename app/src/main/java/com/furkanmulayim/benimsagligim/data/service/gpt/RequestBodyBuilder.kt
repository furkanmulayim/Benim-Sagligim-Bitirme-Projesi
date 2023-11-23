package com.furkanmulayim.benimsagligim.data.service.gpt

class RequestBodyBuilder {
    fun build(gptQuery: String): String {
        return """
            {
            "prompt": "$gptQuery",
            "max_tokens": 100,
            "temperature": 1.1,
            "n": 1}
            """.trimIndent()
    }
}