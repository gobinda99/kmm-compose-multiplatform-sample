package com.gobinda.compose.multiplatform.sample.data.db.di

import com.gobinda.compose.multiplatform.sample.common.Context
import com.gobinda.compose.multiplatform.sample.common.createRoomDatabase
import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserDataSource
import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.db.datasource.DogsDataSource
import com.gobinda.compose.multiplatform.sample.data.db.datasource.UserLocalDataSourceImpl
import com.gobinda.compose.multiplatform.sample.data.api.model.request.TokenManager
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseDslModule = module {
    /*
     * Add module declaration here
     * */
     single { createRoomDatabase(get()) }
}

@Module
class DatabaseModule {

    /*
    * Add annotation based dsl module here
    * Example : @Single
    *           fun appDataBase(context: Context) =
    *           createRoomDatabase(context)
    * */

}