package com.arctouch.codechallenge.splash

import android.app.Application
import android.content.Intent
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.data.Cache
import com.arctouch.codechallenge.data.arch.GenreViewModelImpl
import com.arctouch.codechallenge.util.api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SplashInteractor(var output: SplashModule.InteractorOutput?) : SplashModule.Interactor {

    override fun loadGenres(application: Application) {
        GenreViewModelImpl(application).getGenres()?.value?.let {
            Cache.cacheGenres(it)
            output?.loadedGenresSuccess()
        } ?: run {
            api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        output?.loadedGenresError(it.localizedMessage)
                    }
                    .subscribe {
                        async {
                            it.genres.map { genre -> GenreViewModelImpl(application).updateGenre(genre) }
                            Cache.cacheGenres(it.genres)
                            output?.loadedGenresSuccess()
                        }
                    }
        }
    }

    override fun unregister() {
        output = null
    }

}