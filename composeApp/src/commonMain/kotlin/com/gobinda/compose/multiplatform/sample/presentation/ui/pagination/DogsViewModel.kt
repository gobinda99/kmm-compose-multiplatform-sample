package com.gobinda.compose.multiplatform.sample.presentation.ui.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import com.gobinda.compose.multiplatform.sample.data.api.model.DogsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DogsViewModel(
//    private val dogsPagingSource: DogsPagingSource
    private val dogsRepository: DogsRepository
) : ViewModel() {

    private val _dogResponse: MutableStateFlow<PagingData<DogsModel>> =
        MutableStateFlow(PagingData.empty())
    var dogResponse = _dogResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {

//                dogsPagingSource
                dogsRepository.getLocalDogs()
            }.flow.cachedIn(viewModelScope).collect {
                _dogResponse.value = it
            }
        }
    }

}