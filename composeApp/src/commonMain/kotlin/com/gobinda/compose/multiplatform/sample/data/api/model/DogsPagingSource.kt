package com.gobinda.compose.multiplatform.sample.data.api.model

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import coil3.network.HttpException
import com.gobinda.compose.multiplatform.sample.data.DogsRepository
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import io.ktor.utils.io.errors.IOException
import org.koin.core.annotation.Factory

@Factory
class DogsPagingSource (
    private val repository: DogsRepository
) : PagingSource<Int, DogsModel>() {

    override fun getRefreshKey(state: PagingState<Int, DogsModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DogsModel> {
        val page = params.key ?: 1
        val response = repository.getDogs(page, params.loadSize)
        return try {
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(
                e
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                e
            )
        }
    }
}