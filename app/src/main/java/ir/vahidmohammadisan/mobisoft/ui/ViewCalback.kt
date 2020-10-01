package ir.vahidmohammadisan.mobisoft.ui


/**
 * Created by VahidMohammadi on 25/09/2019.
 */
interface ViewCalback {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}
