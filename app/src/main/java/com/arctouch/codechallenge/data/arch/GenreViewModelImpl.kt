package com.arctouch.codechallenge.data.arch

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.arctouch.codechallenge.model.Genre

class GenreViewModelImpl(application: Application) : AndroidViewModel(application), GenreViewModel {

    private val db : AppDatabase? = AppDatabase.getDatabase(application)

    override fun getGenres(): MutableLiveData<List<Genre>>? {
        return db?.cacheDao()?.getCache()
    }

}