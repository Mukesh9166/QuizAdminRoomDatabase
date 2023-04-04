package com.ms.quizapp.data.network


import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        try {
            val result = call.invoke()
            if (result.isSuccessful) {

                return result.body()!!
            } else {

                if (result.code() == 404) {
                    throw FeatureNotFoundException("Failed")
                } else {
                    throw ApiException("Failed")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw ApiException(e.message ?: "")


        }
    }


    suspend fun<T: Any> apirequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let{
                try{
                    message.append(JSONObject(it).getString("message"))
                }catch(e: JSONException){ }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }



}