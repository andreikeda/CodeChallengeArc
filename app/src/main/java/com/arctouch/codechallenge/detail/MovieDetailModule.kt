package com.arctouch.codechallenge.detail

import com.arctouch.codechallenge.model.Movie

interface MovieDetailModule {

    interface Presenter {
        fun onActivityCreated(movie: Movie)
    }

    interface View {
        fun setBackdrop(backdropUrl: String)
        fun setGenres(genres: String)
        fun setOverview(overview: String)
        fun setPoster(posterUrl: String)
        fun setReleaseDate(releaseDate: String)
        fun setTitle(name: String)
    }
}