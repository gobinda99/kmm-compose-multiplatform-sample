/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gobinda.compose.multiplatform.sample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gobinda.compose.multiplatform.sample.data.db.dao.ModelDao
import com.gobinda.compose.multiplatform.sample.data.db.dao.RemoteKeysDao
import com.gobinda.compose.multiplatform.sample.data.db.dao.UserDao
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import com.gobinda.compose.multiplatform.sample.data.db.model.RemoteKeys
import com.gobinda.compose.multiplatform.sample.domain.model.User

@Database(entities = [User::class, DogsModel::class, RemoteKeys::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun modelDao(): ModelDao
    abstract fun remoteKeyDao(): RemoteKeysDao

}

internal const val dbFileName = "sample.db"