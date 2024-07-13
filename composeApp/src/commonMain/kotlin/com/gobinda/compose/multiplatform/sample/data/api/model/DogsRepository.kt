package com.gobinda.compose.multiplatform.sample.data.api.model

import androidx.paging.PagingSource
import com.gobinda.compose.multiplatform.sample.data.api.datasource.RestDataSource
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import com.gobinda.compose.multiplatform.sample.data.db.datasource.ModelDataSource

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
//        dataSource.insertModel(data)
        return data
    }

     fun getLocalDogs(): PagingSource<Int, DogsModel> = dataSource.getPagingModels()

     suspend fun getLocalDogs(pageIndex: Int, pageSize: Int) = dataSource.getAllDogs(pageIndex, pageIndex)



}