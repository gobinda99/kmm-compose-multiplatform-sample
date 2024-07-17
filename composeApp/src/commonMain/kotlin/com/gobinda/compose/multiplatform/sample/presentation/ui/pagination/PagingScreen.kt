package com.gobinda.compose.multiplatform.sample.presentation.ui.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.gobinda.compose.multiplatform.sample.domain.model.DogsModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PagingScreen(
    modifier: Modifier = Modifier,
    viewModel: PagingViewModel = koinViewModel()
    /* viewModel: PagingMediatorViewModel = koinViewModel()*/
) {
    val response = viewModel.dogResponse.collectAsLazyPagingItems()

    PagingContent(modifier, response)

}


@Composable
private fun PagingContent(
    modifier: Modifier,
    response: LazyPagingItems<DogsModel>
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = modifier.fillMaxSize()
    ) {

        items(response.itemCount) {
            AsyncImage(
                model = response[it]?.url ?: "-",
                /* placeholder = painterResource(Res.drawable.ic_cyclone),*/
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(20.dp)
                    .clip(CircleShape)
            )

        }

    }

    response.apply {
        when {
            loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }

            }

            loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                Text(text = "Error")
            }

            loadState.refresh is LoadState.NotLoading -> {
            }
        }
    }
}
