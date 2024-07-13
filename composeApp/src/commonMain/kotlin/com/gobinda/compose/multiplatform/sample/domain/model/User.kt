package com.gobinda.compose.multiplatform.sample.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val password: String
)