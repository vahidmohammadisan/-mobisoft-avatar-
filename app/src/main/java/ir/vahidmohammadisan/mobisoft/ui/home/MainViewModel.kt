package ir.vahidmohammadisan.mobisoft.ui.home

import androidx.lifecycle.ViewModel
import ir.vahidmohammadisan.mobisoft.data.repositories.Repository
import ir.vahidmohammadisan.mobisoft.ui.ViewCalback
import ir.vahidmohammadisan.mobisoft.util.lazyDeferred

/**
 * Created by VahidMohammadi on 03/09/2019.
 */
class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    var viewCalback: ViewCalback? = null
    var MovieID: String? = null


    val MovieList by lazyDeferred {
        repository.getMovies()
    }

    val MovieDetails by lazyDeferred {
        repository.getMovieDetails(MovieID!!)
    }

    fun clearMovieData() = repository.clearMovieData()
    fun clearMovieDetailsData() = repository.clearMovieDetailsData()

}