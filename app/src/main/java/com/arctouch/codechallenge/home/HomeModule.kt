package com.arctouch.codechallenge.home

import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.SearchingMoviesResponse
import com.arctouch.codechallenge.model.UpcomingMoviesResponse

interface HomeModule {

    interface Interactor {
        fun loadMovies(page: Long)
        fun searchMovies(query: String, page: Long)
        fun unregister()
    }

    interface InteractorOutput {
        fun loadedMoviesError(errorMessage: String)
        fun loadedMoviesSuccess(response: UpcomingMoviesResponse)
        fun searchedMoviesError(errorMessage: String)
        fun searchedMoviesSuccess(response: SearchingMoviesResponse)
    }

    interface Presenter {
        fun callMoviesApi(page: Long)
        fun callSearchMoviesApi(query: String, page: Long)
        fun onMovieItemClicked(movie: Movie)
        fun unregister()
    }

    interface Router {
        fun goToDetailScreen(movie: Movie)
        fun unregister()
    }

    interface View {
        fun hideLoading()
        fun loadedMovies(movies: List<Movie>)
        fun setPage(page: Long)
        fun setHasMorePages(hasMorePages: Boolean)
        fun showError(errorMessage: String)
        fun showLoading()
    }

}