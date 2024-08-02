package com.gobinda.compose.multiplatform.sample.data.db.di

import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.common.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
class DatabaseModule {

    @Single
    fun appDataBase(context: Context): AppDatabase {
        return createRoomDatabase(context)
    }

}