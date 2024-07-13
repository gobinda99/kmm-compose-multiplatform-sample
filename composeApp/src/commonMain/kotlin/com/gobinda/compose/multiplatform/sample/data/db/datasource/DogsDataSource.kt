package com.gobinda.compose.multiplatform.sample.data.db.datasource

import androidx.paging.PagingSource
import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DogsDataSource (db: AppDatabase) {

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

    suspend fun getAllDogs(pageIndex: Int, pageSize: Int): List<DogsModel>{

        return dao.getAllDogs(pageIndex, pageSize)
    }



}