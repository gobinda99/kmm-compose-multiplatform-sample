package com.gobinda.compose.multiplatform.sample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteKey")
data class RemoteKeys(
    @PrimaryKey
    val repoId:String,
    val prevKey:Int?,
    val nextKey:Int?
)