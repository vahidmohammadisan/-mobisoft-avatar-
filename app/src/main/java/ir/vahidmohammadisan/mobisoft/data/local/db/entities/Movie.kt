package ir.vahidmohammadisan.mobisoft.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val Title: String? = null,
    val Year: String? = null,
    val imdbID: String? = null,
    val Type: String? = null,
    val Poster: String? = null
)

