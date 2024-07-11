package com.gobinda.compose.multiplatform.sample.data.source.local.room

import androidx.paging.PagingSource
import androidx.room.Query
import com.gobinda.compose.multiplatform.sample.data.DogsModel
import com.gobinda.compose.multiplatform.sample.data.User
import com.gobinda.compose.multiplatform.sample.data.source.UserDataSource
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ModelDataSource (db: AppDatabase) {

    val dispatcher : CoroutineDispatcher = Dispatchers.IO

   val dao = db.modelDao()

    suspend fun insertModel(data: List<DogsModel>){
        withContext(dispatcher){
            dao.insert(data)
        }
    }

    fun getPagingModels(): PagingSource<Int, DogsModel> {

       return dao.getAllDogs()
    }



}