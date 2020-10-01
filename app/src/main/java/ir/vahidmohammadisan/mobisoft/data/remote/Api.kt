package ir.vahidmohammadisan.mobisoft.data.remote

import com.google.gson.GsonBuilder
import ir.vahidmohammadisan.mobisoft.data.model.*
import ir.vahidmohammadisan.mobisoft.util.BaseUrl
import ir.vahidmohammadisan.mobisoft.util.UnsafeOkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * Created by VahidMohammadi on 03/09/2019.
 */
interface Api {

    @GET("/?apikey=4f588b70&s=avatar")
    suspend fun getMovieList(): Response<MovieTotal>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            unsafeOkHttpClient: UnsafeOkHttpClient
        ): Api {

            val okHttpClient = unsafeOkHttpClient.unsafeOkHttpClient

/*            val logging = HttpLoggingInterceptor()
            logging.apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }*/

/*            val okkHttpclient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(logging)
                .build()*/

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api::class.java)
        }
    }

}