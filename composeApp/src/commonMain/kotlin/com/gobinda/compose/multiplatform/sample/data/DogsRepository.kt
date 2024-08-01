package com.gobinda.compose.multiplatform.sample.data

import androidx.paging.PagingSource
import com.gobinda.compose.multiplatform.sample.data.api.datasource.RestDataSource
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import com.gobinda.compose.multiplatform.sample.data.db.datasource.DogsDataSource
import org.koin.core.annotation.Factory

@Factory
class DogsRepository(
    private val apiService: RestDataSource,
    private val dataSource: DogsDataSource
) {
    suspend fun getDogs(
        page: Int,
        limit: Int
    ): List<DogsModel> {
        val data = apiService.getAllDogs(
            page, limit
        )
        return data
    }

    fun getLocalDogs(): PagingSource<Int, DogsModel> = dataSource.getPagingModels()

    suspend fun getLocalDogs(pageIndex: Int, pageSize: Int) =
        dataSource.getAllDogs(pageIndex, pageSize)


}