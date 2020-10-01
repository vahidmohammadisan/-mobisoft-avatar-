package ir.vahidmohammadisan.mobisoft.data.repositories

import androidx.lifecycle.LiveData
import ir.vahidmohammadisan.mobisoft.data.local.db.AppDatabase
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.Movie
import ir.vahidmohammadisan.mobisoft.data.local.preferences.PreferenceProvider
import ir.vahidmohammadisan.mobisoft.data.remote.Api
import ir.vahidmohammadisan.mobisoft.data.remote.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by VahidMohammadi on 16/09/2019.
 */

class Repository(
    private val api: Api,
    private val prefs: PreferenceProvider,
    private val db: AppDatabase
) : SafeApiRequest() {

    fun clearAllData() = db.clearAllData()

    suspend fun getMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            fetchMovies()
            db.getMovieDao().getAll()
        }
    }

    private suspend fun fetchMovies() {
        try {
            val response = apiRequest { api.getMovieList() }
            db.getMovieDao().clearAll()
            db.getMovieDao()
            db.getMovieDao().saveAll(response.Search)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}