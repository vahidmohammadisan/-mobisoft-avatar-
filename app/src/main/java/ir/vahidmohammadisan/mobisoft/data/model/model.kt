package ir.vahidmohammadisan.mobisoft.data.model

import ir.vahidmohammadisan.mobisoft.data.local.db.entities.Movie

data class MovieTotal(
    var Search: List<Movie>,
    var totalResults: String,
    var Response: String?
)

data class MovieRating(
    var Source: String,
    var Value: String?
)