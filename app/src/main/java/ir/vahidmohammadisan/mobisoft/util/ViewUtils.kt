package ir.vahidmohammadisan.mobisoft.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.library.BuildConfig
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

/**
 * Created by VahidMohammadi on 03/09/2019.
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}


fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .override(300, 300)
        //.placeholder(R.drawable.placeholder)
        .into(this)
}


fun printLog(message: String) {
    if (BuildConfig.DEBUG) {
        Log.w(AppTag, message)
    }
}

fun String.matches(regex: Regex): Boolean {
    return regex.matches(this)
}
