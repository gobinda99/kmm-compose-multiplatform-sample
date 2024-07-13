package com.gobinda.compose.multiplatform.sample.common

import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase

expect fun createRoomDatabase(context: Context): AppDatabase