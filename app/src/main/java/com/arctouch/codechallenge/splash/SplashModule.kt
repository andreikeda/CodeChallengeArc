package com.arctouch.codechallenge.splash

import com.arctouch.codechallenge.model.Genre

interface SplashModule {

    interface Interactor {
        fun loadGenres()
        fun unregister()
    }

    interface InteractorOutput {
        fun loadedGenresError(errorMessage: String)
        fun loadedGenresSuccess(genres: List<Genre>)
    }

    interface Presenter {
        fun callGenresApi()
        fun unregister()
    }

    interface Router {
        fun goToHomeScreen()
        fun unregister()
    }

    interface View {
        fun loadedGenres(genres: List<Genre>)
        fun showError(errorMessage: String)
    }
}