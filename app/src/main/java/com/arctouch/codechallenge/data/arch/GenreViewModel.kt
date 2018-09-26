package com.arctouch.codechallenge.data.arch

import android.arch.lifecycle.LiveData
import com.arctouch.codechallenge.model.Genre

interface GenreViewModel {

    fun getGenres() : LiveData<List<Genre>>?
    fun updateGenre(genre: Genre)

}