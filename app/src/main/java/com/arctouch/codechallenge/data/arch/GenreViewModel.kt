package com.arctouch.codechallenge.data.arch

import android.arch.lifecycle.MutableLiveData
import com.arctouch.codechallenge.model.Genre

interface GenreViewModel {

    fun getGenres() : MutableLiveData<List<Genre>>?

}