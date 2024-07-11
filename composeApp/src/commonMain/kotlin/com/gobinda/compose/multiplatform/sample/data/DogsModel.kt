package com.gobinda.compose.multiplatform.sample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "pagination_data")
data class DogsModel(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @SerialName("url")
    @ColumnInfo
    val url: String
)
