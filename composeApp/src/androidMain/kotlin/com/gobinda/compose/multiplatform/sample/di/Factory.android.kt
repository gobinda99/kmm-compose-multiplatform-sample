package com.gobinda.compose.multiplatform.sample.di

import android.app.Application
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gobinda.compose.multiplatform.sample.data.source.local.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.local.dbFileName
import kotlinx.coroutines.Dispatchers

actual class Factory(private val app: Application) {
    actual fun createRoomDatabase(): AppDatabase {
        TODO()
       /* val dbFile = app.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(app, dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()*/
    }
}