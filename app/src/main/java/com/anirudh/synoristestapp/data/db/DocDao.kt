package com.anirudh.synoristestapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anirudh.synoristestapp.data.db.entities.LocalDocData

@Dao
interface DocDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertDocList(localDocData: List<LocalDocData>)

    @Query("SELECT * FROM LocalDocData")
    fun getDocList() : List<LocalDocData>
}