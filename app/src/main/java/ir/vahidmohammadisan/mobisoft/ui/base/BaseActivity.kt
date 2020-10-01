package ir.vahidmohammadisan.mobisoft.ui.base

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BaseActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val res = resources
        val newConfig = res.configuration
        val locale = Locale("fa", "IR")
        newConfig.locale = locale
        newConfig.setLayoutDirection(locale)
        res.updateConfiguration(newConfig, res.displayMetrics)

    }
}