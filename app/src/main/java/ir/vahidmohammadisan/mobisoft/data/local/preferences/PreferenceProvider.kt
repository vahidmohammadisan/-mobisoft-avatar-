package ir.vahidmohammadisan.mobisoft.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private const val TOKEN = "token"
private const val Phone = "Phone"
private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveToken(saved: String) {
        preference.edit().putString(
            TOKEN,
            saved
        ).apply()
    }

    fun getToken(): String? {//LiveData<String>? {
        // val liveToken = MutableLiveData<String>()
        // liveToken.value = preference.getString(TOKEN, null)
        return preference.getString(TOKEN, null)//liveToken
    }

    fun savePhone(saved: String) {
        preference.edit().putString(
            Phone,
            saved
        ).apply()
    }

    fun getPhone(): LiveData<String>? {
        val phone = MutableLiveData<String>()
        phone.value = preference.getString(Phone, null)
        return phone
    }

    fun savelastSavedAt(savedAt: String) {
        preference.edit().putString(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getLastSavedAt(): String? {
        return preference.getString(KEY_SAVED_AT, null)
    }

}