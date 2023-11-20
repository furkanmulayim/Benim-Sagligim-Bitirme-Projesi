package com.furkanmulayim.benimsagligim.util

import android.util.Log
import com.furkanmulayim.benimsagligim.data.service.gpt.APIClient
import com.furkanmulayim.benimsagligim.data.service.gpt.RequestBodyBuilder
import com.furkanmulayim.benimsagligim.data.service.gpt.ResponseParser
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

fun getGPTResponse(gptQuery: String, callback: (String) -> Unit) {
    val apiClient = APIClient()
    val requestBodyBuilder = RequestBodyBuilder()
    val responseParser = ResponseParser()

    val requestBody = requestBodyBuilder.build(gptQuery)

    apiClient.post(requestBody, object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e(e.message, "Request Hatası!")
        }

        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            val textResult = responseParser.parse(body!!)
            callback(textResult!!)
        }
    })
}