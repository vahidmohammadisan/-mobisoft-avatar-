package ir.vahidmohammadisan.mobisoft.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ir.vahidmohammadisan.mobisoft.R
import ir.vahidmohammadisan.mobisoft.ui.ViewCalback
import ir.vahidmohammadisan.mobisoft.ui.base.BaseFragment
import ir.vahidmohammadisan.mobisoft.ui.home.MainViewModel
import ir.vahidmohammadisan.mobisoft.ui.home.MainViewModelFactory
import ir.vahidmohammadisan.mobisoft.util.*
import kotlinx.android.synthetic.main.item_movie_fragment.poster
import kotlinx.android.synthetic.main.item_movie_fragment.title
import kotlinx.android.synthetic.main.main_fragment.progress
import kotlinx.android.synthetic.main.movie_details_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by VahidMohammadi on 15/09/2019.
 */
class MovieDetailsFragment : BaseFragment(), ViewCalback, KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: MainViewModel
    private val factory by instance<MainViewModelFactory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.viewCalback = this
        viewModel.MovieID = arguments!!.getString(MovieID)

        Coroutines.main {
            progress.show()
            viewModel!!.MovieDetails.await().observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    progress.hide()
                    context?.let { it1 -> poster.loadImage(it1, it.Poster.toString()) }
                    title.text = it.Genre
                    title2.text = it.Actors
                    title3.text = it.Language
                    title4.text = it.imdbRating
                }
            })
        }

    }


    override fun onStarted() {

    }

    override fun onSuccess(message: String) {

    }

    override fun onFailure(message: String) {

    }

}