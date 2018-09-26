package com.arctouch.codechallenge.splash

import android.app.Application
import com.arctouch.codechallenge.model.Genre

interface SplashModule {

    interface Interactor {
        fun loadGenres(application: Application)
        fun unregister()
    }

    interface InteractorOutput {
        fun loadedGenresError(errorMessage: String)
        fun loadedGenresSuccess()
    }

    interface Presenter {
        fun callGenresApi(application: Application)
        fun unregister()
    }

    interface Router {
        fun goToHomeScreen()
        fun unregister()
    }

    interface View {
        fun showError(errorMessage: String)
    }
}