package com.arctouch.codechallenge.data.arch

import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.arctouch.codechallenge.model.Genre

private const val QUERY_SELECT_ALL = "select * from genreData"

@Dao
interface CacheDao {

    @Query(QUERY_SELECT_ALL)
    fun getCache() : MutableLiveData<List<Genre>>

}