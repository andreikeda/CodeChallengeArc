package com.arctouch.codechallenge.detail

import com.arctouch.codechallenge.model.Movie

class MovieDetailPresenter(var view: MovieDetailModule.View?) : MovieDetailModule.Presenter {

    override fun onActivityCreated(movie: Movie) {
        movie.backdropPath?.let { view?.setBackdrop(it) }
        movie.genres?.let {
            var genres = ""
            for (i in 0 until it.size) {
                genres += it[i].name
                if (i < (it.size - 1)) {
                    genres += ", "
                }
            }
            view?.setGenres(genres)
        }
        movie.overview?.let { view?.setOverview(it) }
        movie.posterPath?.let { view?.setPoster(it) }
        movie.releaseDate?.let { view?.setReleaseDate(it) }
        view?.setTitle(movie.title)
    }

    override fun unregister() {
        view = null
    }

}