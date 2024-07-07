package com.gobinda.compose.multiplatform.sample.data.source.local

import android.annotation.SuppressLint
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.gobinda.compose.multiplatform.sample.common.Context
import kotlinx.coroutines.Dispatchers

actual fun createRoomDatabase(context: Context): AppDatabase {
    println("hello 1234 database")
     val dbFile = context.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(context, dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
}