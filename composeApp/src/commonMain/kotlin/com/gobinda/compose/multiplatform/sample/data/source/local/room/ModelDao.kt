package com.gobinda.compose.multiplatform.sample.data.source.local.room

import androidx.room.*
import app.cash.paging.PagingSource
import com.gobinda.compose.multiplatform.sample.data.DogsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<DogsModel>)

    @Query("SELECT * FROM pagination_data")
    fun getAllDogs():PagingSource<Int, DogsModel>

    @Query("DELETE FROM pagination_data")
    suspend fun clearAll()

}

