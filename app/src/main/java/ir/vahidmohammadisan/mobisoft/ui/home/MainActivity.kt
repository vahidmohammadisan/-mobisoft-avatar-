package ir.vahidmohammadisan.mobisoft.ui.home

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ir.vahidmohammadisan.mobisoft.R
import ir.vahidmohammadisan.mobisoft.databinding.ActivityMainBinding
import ir.vahidmohammadisan.mobisoft.ui.ViewCalback
import ir.vahidmohammadisan.mobisoft.ui.base.BaseActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : BaseActivity(), ViewCalback, KodeinAware {

    override val kodein by kodein()
    private val factory by instance<MainViewModelFactory>()

    //private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var viewModel: MainViewModel? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        binding.viewModel = viewModel

        viewModel!!.viewCalback = this

        //bottomSheetBehavior = BottomSheetBehavior.from(forgetPass)
/*
        forget_pass.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
*/

    }

    override fun onStarted() {

    }

    override fun onSuccess(message: String) {

    }

    override fun onFailure(message: String) {

    }

}