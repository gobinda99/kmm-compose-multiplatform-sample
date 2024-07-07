package com.gobinda.compose.multiplatform.sample.di

import com.gobinda.compose.multiplatform.sample.data.source.local.AppDatabase

expect class Factory {
    fun createRoomDatabase(): AppDatabase
}