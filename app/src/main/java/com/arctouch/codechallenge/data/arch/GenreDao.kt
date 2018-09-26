package com.arctouch.codechallenge.data.arch

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.arctouch.codechallenge.model.Genre

private const val QUERY_SELECT_ALL = "select * from genreData"

@Dao
interface GenreDao {

    @Query(QUERY_SELECT_ALL)
    fun getCache() : LiveData<List<Genre>>

    @Insert(onConflict = REPLACE)
    fun updateCache(genre: Genre)

}