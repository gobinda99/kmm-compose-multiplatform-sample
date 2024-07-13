package com.gobinda.compose.multiplatform.sample.data.db.dao

import androidx.room.*
import app.cash.paging.PagingSource
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel

@Dao
interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<DogsModel>)

    @Query("SELECT * FROM pagination_data")
    fun getAllDogs():PagingSource<Int, DogsModel>

    @Query("DELETE FROM pagination_data")
    suspend fun clearAll()

    @Query("SELECT * FROM pagination_data LIMIT :pageSize OFFSET :pageIndex")
    suspend fun getAllDogs(pageIndex: Int, pageSize: Int ) : List<DogsModel>

}

