package ir.vahidmohammadisan.mobisoft.data.remote

import ir.vahidmohammadisan.mobisoft.util.ApiException
import ir.vahidmohammadisan.mobisoft.util.CustomMsg
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest() {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString(CustomMsg))
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                //  popup_message.append("\n")
            }

            // popup_message.append("Error Code: ${response.code()}")

            /*  if (response.code() == 426) {
                  throw ForceChangePassException(popup_message.toString())
              } else {*/
            throw ApiException(message.toString())
            // }
        }
    }
}