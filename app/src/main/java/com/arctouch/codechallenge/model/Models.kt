package com.arctouch.codechallenge.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

data class GenreResponse(val genres: List<Genre>)

@Entity(tableName = "genreData")
data class Genre(@PrimaryKey(autoGenerate = true) var id: Int?, val name: String) : Serializable {
    @Ignore constructor() : this(null, "")
}

data class UpcomingMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)

data class SearchingMoviesResponse(
        val page: Int,
        val results: List<Movie>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val genres: List<Genre>?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String?
) : Serializable
