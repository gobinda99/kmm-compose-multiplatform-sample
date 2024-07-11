package com.gobinda.compose.multiplatform.sample.data.source.remote.ktor

import androidx.paging.PagingSource
import com.gobinda.compose.multiplatform.sample.data.DogsModel
import com.gobinda.compose.multiplatform.sample.data.source.local.room.ModelDataSource

class DogsRepository (
    private val apiService: RestDataSource,
    private val dataSource: ModelDataSource
) {
    suspend fun getDogs(
        page: Int,
        limit: Int
    ): List<DogsModel> {
        val data = apiService.getAllDogs(
            page, limit
        )
        dataSource.insertModel(data)
        return data
    }

     fun getLocalDogs(): PagingSource<Int, DogsModel> = dataSource.getPagingModels()



}