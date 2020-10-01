package ir.vahidmohammadisan.mobisoft.ui.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ir.vahidmohammadisan.mobisoft.R
import ir.vahidmohammadisan.mobisoft.ui.home.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(runnable, 1000)

    }

    internal var runnable: Runnable = Runnable {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}