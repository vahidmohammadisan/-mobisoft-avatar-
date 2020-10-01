package ir.vahidmohammadisan.mobisoft.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.Movie
import ir.vahidmohammadisan.mobisoft.data.local.db.entities.MovieDetails

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(movie: List<Movie>)

    @Query("SELECT * FROM `Movie`")
    fun getAll(): LiveData<List<Movie>>

    @Query("DELETE FROM `Movie`")
    fun clearAll(): Unit
}

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieDetails)

    @Query("SELECT * FROM `MovieDetails` WHERE imdbID=:imdbID")
    fun getMovieDetailsWithId(imdbID: String): LiveData<MovieDetails>

    @Query("DELETE FROM `MovieDetails`")
    fun clearAllDetails(): Unit
}