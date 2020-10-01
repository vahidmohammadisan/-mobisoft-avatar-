package ir.vahidmohammadisan.mobisoft.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(movie: List<Movie>)

    @Query("SELECT * FROM `Movie`")
    fun getAll(): LiveData<List<Movie>>

    @Query("DELETE FROM `Movie`")
    fun clearAll(): Unit
}