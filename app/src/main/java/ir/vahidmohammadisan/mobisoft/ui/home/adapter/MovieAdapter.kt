package ir.vahidmohammadisan.mobisoft.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ir.vahidmohammadisan.mobisoft.BR
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.Movie
import ir.vahidmohammadisan.mobisoft.util.loadImage
import kotlinx.android.synthetic.main.item_movie_fragment.view.*

/**
 * Created by VahidMohammadi on 23/09/2019.
 */

class MovieAdapter(val data: List<Movie>, val layout: Int) :
    RecyclerView.Adapter<MovieHolder>() {

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, layout, parent, false)

        return MovieHolder(
            binding,
            parent.context,
            parent
        )
    }

    override fun getItemCount(): Int = data.size

}

class MovieHolder(
    val binding: ViewDataBinding,
    private var context: Context,
    private val view: View
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Movie) {

        binding.setVariable(BR.movie, data)
        binding.executePendingBindings()

        try {
            binding.root.poster.loadImage(context, data.Poster.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

/*        var bundle = bundleOf(festivalId!! to data.id.toString())
        binding.root.setOnClickListener {
            view.findNavController().navigate(R.id.festivalDetailsF, bundle)
        }*/

    }
}