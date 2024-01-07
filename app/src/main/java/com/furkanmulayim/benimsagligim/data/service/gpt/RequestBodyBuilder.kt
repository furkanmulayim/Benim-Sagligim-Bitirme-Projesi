package com.furkanmulayim.benimsagligim.data.service.gpt

class RequestBodyBuilder {
    fun build(gptQuery: String): String {
        return """
            {
            "prompt": "Can you briefly and concisely give information about drug $gptQuery and what it does? Could you please give your answer using Turkish language?",
            "max_tokens": 100,
            "temperature": 1.0,
            "n": 1}
            """.trimIndent()
    }
}