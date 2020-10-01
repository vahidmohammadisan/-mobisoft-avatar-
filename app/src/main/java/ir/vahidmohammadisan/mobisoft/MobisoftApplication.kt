package ir.vahidmohammadisan.mobisoft

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.vahidmohammadisan.mobisoft.data.local.db.AppDatabase
import ir.vahidmohammadisan.mobisoft.data.local.preferences.PreferenceProvider
import ir.vahidmohammadisan.mobisoft.data.remote.Api
import ir.vahidmohammadisan.mobisoft.data.remote.NetworkConnectionInterceptor
import ir.vahidmohammadisan.mobisoft.data.repositories.Repository
import ir.vahidmohammadisan.mobisoft.ui.home.MainViewModelFactory
import ir.vahidmohammadisan.mobisoft.ui.home.fragment.MainFragment
import ir.vahidmohammadisan.mobisoft.util.UnsafeOkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MobisoftApplication : MultiDexApplication(), KodeinAware {

    private var appInstance: Context? = null

    companion object {

        @JvmField
        var appInstance: MobisoftApplication? = null


        @JvmStatic
        fun getAppInstance(): MobisoftApplication {
            return appInstance as MobisoftApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = applicationContext!!
        MultiDex.install(this);
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MobisoftApplication))

        bind() from singleton {
            NetworkConnectionInterceptor(
                instance()
            )
        }
        bind() from singleton { UnsafeOkHttpClient() }
        bind() from singleton { Api(instance(), instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { Repository(instance(), instance(), instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
        bind() from provider { MainFragment() }

    }

    fun getNormalTypeFace(): Typeface {
        return ResourcesCompat.getFont(getAppInstance(), R.font.sans)!!
    }

}