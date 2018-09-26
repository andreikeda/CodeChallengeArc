package com.arctouch.codechallenge.splash

import android.content.Intent
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.util.api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashInteractor(var output: SplashModule.InteractorOutput?) : SplashModule.Interactor {

    override fun loadGenres() {
        api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    output?.loadedGenresError(it.localizedMessage)
                }
                .subscribe {
                    output?.loadedGenresSuccess(it.genres)
                }
    }

    override fun unregister() {
        output = null
    }

}