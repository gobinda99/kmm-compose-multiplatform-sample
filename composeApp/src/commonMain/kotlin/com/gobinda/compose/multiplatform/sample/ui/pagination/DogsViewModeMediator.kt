package com.gobinda.compose.multiplatform.sample.ui.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.map
import com.gobinda.compose.multiplatform.sample.data.DogsModel
import com.gobinda.compose.multiplatform.sample.data.source.DogsRemoteMediator
import com.gobinda.compose.multiplatform.sample.data.source.local.room.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.DogsPagingSource
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.DogsRepository
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.RestDataSource
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class DogsViewModeMediator(
    private val db:AppDatabase,private val apiService: RestDataSource,
    private val pagingSource: DogsPagingSource

) : ViewModel() {

    private val _dogResponse: MutableStateFlow<PagingData<DogsModel>> =
        MutableStateFlow(PagingData.empty())
    var dogResponse = _dogResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(20, enablePlaceholders = false),
//                pagingSourceFactory = { db.modelDao().getAllDogs() },
                pagingSourceFactory = { pagingSource },
                remoteMediator = DogsRemoteMediator(db, apiService)
            ).flow.flowOn(Dispatchers.IO).
            cachedIn(viewModelScope).collect{
                _dogResponse.value = it
            }
        }
    }

}