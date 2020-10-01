package ir.vahidmohammadisan.mobisoft.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "mobisoft.db"
            ).build()
    }

    fun clearAllData() {
        getMovieDao().clearAll()
    }

}