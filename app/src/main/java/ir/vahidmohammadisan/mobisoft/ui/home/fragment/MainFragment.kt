package ir.vahidmohammadisan.mobisoft.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ir.vahidmohammadisan.mobisoft.R
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.Movie
import ir.vahidmohammadisan.mobisoft.ui.ViewCalback
import ir.vahidmohammadisan.mobisoft.ui.base.BaseFragment
import ir.vahidmohammadisan.mobisoft.ui.home.MainViewModel
import ir.vahidmohammadisan.mobisoft.ui.home.MainViewModelFactory
import ir.vahidmohammadisan.mobisoft.ui.home.adapter.MovieAdapter
import ir.vahidmohammadisan.mobisoft.util.Coroutines
import ir.vahidmohammadisan.mobisoft.util.hide
import ir.vahidmohammadisan.mobisoft.util.show
import kotlinx.android.synthetic.main.main_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by VahidMohammadi on 15/09/2019.
 */
class MainFragment : BaseFragment(), ViewCalback, KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: MainViewModel
    private val factory by instance<MainViewModelFactory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.viewCalback = this

        Coroutines.main {
            progress.show()
            viewModel!!.MovieList.await().observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    progress.hide()
                    initMovieRecyclerView(it)
                }
            })
        }

    }

    private fun initMovieRecyclerView(movieList: List<Movie>) {

        val mAdapter = MovieAdapter(
            movieList,
            R.layout.item_movie_fragment
        )
        recyclerviewMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }

    override fun onStarted() {

    }

    override fun onSuccess(message: String) {

    }

    override fun onFailure(message: String) {

    }

}