package com.gobinda.compose.multiplatform.sample.common

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gobinda.compose.multiplatform.sample.data.source.local.room.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.local.room.dbFileName
import kotlinx.coroutines.Dispatchers

actual fun createRoomDatabase(context: Context): AppDatabase {
     val dbFile = context.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(context, dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
}