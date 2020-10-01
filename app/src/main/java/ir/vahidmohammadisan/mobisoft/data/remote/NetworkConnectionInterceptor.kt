package ir.vahidmohammadisan.mobisoft.data.remote

import android.content.Context
import android.net.ConnectivityManager
import ir.vahidmohammadisan.mobisoft.util.NoInternetException
import ir.vahidmohammadisan.mobisoft.util.SURE_CONNECTION
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable())
            throw NoInternetException(SURE_CONNECTION!!)

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}