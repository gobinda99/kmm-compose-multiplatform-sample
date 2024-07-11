package com.gobinda.compose.multiplatform.sample.data.source.remote.ktor

import com.gobinda.compose.multiplatform.sample.data.DogsModel

class DogsRepository (
    private val apiService: RestDataSource
) {
    suspend fun getDogs(
        page: Int,
        limit: Int
    ): List<DogsModel> = apiService.getAllDogs(
        page, limit
    )

}