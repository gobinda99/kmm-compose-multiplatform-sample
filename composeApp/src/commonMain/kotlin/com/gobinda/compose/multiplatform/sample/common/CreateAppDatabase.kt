package com.gobinda.compose.multiplatform.sample.common

import com.gobinda.compose.multiplatform.sample.data.source.local.room.AppDatabase

expect fun createRoomDatabase(context: Context): AppDatabase