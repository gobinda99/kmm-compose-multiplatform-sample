package com.gobinda.compose.multiplatform.sample.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gobinda.compose.multiplatform.sample.data.db.model.RemoteKeys

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemote(list: List<RemoteKeys>)

    @Query("SELECT * FROM remoteKey WHERE repoId = :id")
    suspend fun getRemoteKeys(id:String) : RemoteKeys?

    @Query("DELETE FROM remoteKey")
    suspend fun clearAll()
}