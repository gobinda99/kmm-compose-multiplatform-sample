package com.gobinda.compose.multiplatform.sample.presentation.ui.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.PagingSource
import app.cash.paging.cachedIn
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import com.gobinda.compose.multiplatform.sample.data.paging.DogsRemoteMediator
import com.gobinda.compose.multiplatform.sample.data.db.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.api.model.DogsPagingSource
import com.gobinda.compose.multiplatform.sample.data.api.datasource.RestDataSource
import com.gobinda.compose.multiplatform.sample.data.paging.DogsPagingSourceDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@OptIn(ExperimentalPagingApi::class)
@KoinViewModel
class PagingMediatorViewModel(
    private val db: AppDatabase, private val apiService: RestDataSource,
//    private val pagingSource: DogsPagingSource
    private val pagingSource: PagingSource<Int, DogsModel>,
    private val pagingSource1: DogsPagingSourceDatabase

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