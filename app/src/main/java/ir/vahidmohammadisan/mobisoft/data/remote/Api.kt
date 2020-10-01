package ir.vahidmohammadisan.mobisoft.data.remote

import com.google.gson.GsonBuilder
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.MovieDetails
import ir.vahidmohammadisan.mobisoft.data.model.MovieTotal
import ir.vahidmohammadisan.mobisoft.util.BaseUrl
import ir.vahidmohammadisan.mobisoft.util.UnsafeOkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by VahidMohammadi on 03/09/2019.
 */
interface Api {

    @GET("/")
    suspend fun getMovieList(
        @Query("s") p1: String,
        @Query("apikey") p2: String
    ): Response<MovieTotal>

    @GET("/")
    suspend fun getMovieDetailsList(
        @Query("i") p1: String,
        @Query("apikey") p2: String
    ): Response<MovieDetails>

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