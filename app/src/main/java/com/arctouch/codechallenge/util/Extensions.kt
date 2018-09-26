package com.arctouch.codechallenge.util

import android.content.Context
import android.content.Intent
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.detail.MovieDetailActivity
import com.arctouch.codechallenge.model.Movie
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val EXTRA_MOVIE = "eMovie"

val api: TmdbApi = Retrofit.Builder()
        .baseUrl(TmdbApi.URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(TmdbApi::class.java)

fun Context.buildMovieDetailIntent(movie: Movie) : Intent {
    return Intent(this, MovieDetailActivity::class.java).apply {
        putExtra(EXTRA_MOVIE, movie)
    }
}