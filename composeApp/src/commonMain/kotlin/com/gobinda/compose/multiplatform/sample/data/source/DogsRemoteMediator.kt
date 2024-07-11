package com.gobinda.compose.multiplatform.sample.data.source


import androidx.room.useWriterConnection
import app.cash.paging.ExperimentalPagingApi
import app.cash.paging.LoadType
import app.cash.paging.PagingState
import app.cash.paging.RemoteMediator
import coil3.network.HttpException
import com.gobinda.compose.multiplatform.sample.data.DogsModel
import com.gobinda.compose.multiplatform.sample.data.RemoteKeys
import com.gobinda.compose.multiplatform.sample.data.source.local.room.AppDatabase
import com.gobinda.compose.multiplatform.sample.data.source.remote.ktor.RestDataSource
import io.github.aakira.napier.Napier
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
@ExperimentalPagingApi
class DogsRemoteMediator (
        private val db:AppDatabase,
        private val apiService: RestDataSource
)  : RemoteMediator<Int,DogsModel>() {
    private val STARTING_PAGE_INDEX = 1

    override suspend fun initialize(): InitializeAction {
       return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, DogsModel>): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page  = when(pageKeyData){
            is MediatorResult.Success ->{
                return pageKeyData
            }else->{
                pageKeyData as Int
            }
        }

      try {
          val response = apiService.getAllDogs(page,state.config.pageSize)
          val endOfList = response.isEmpty()
          db.useWriterConnection {
              if(loadType == LoadType.REFRESH){
                  db.remoteKeyDao().clearAll()
                  db.modelDao().clearAll()
              }
              val prevKey = if (page == STARTING_PAGE_INDEX) null else page-1
              val nextKey = if(endOfList) null else page+1
              val keys = response.map {
                  RemoteKeys(it.id,prevKey,nextKey)
              }
              db.remoteKeyDao().insertRemote(keys)
              db.modelDao().insert(response)

          }
           Napier.d("med $endOfList")


          /*db.withTransaction {

          }*/
         return MediatorResult.Success(endOfPaginationReached = endOfList)
      }catch (e: IOException){
       return   MediatorResult.Error(e)
      }catch (e: HttpException){
          return MediatorResult.Error(e)
      }
    }


    private suspend fun getKeyPageData(loadType: LoadType,state: PagingState<Int, DogsModel>) : Any{
        return when(loadType){
            LoadType.REFRESH->{
                val remoteKeys = getRefreshRemoteKey(state)
                remoteKeys?.nextKey?.minus(1)?:STARTING_PAGE_INDEX
            }
            LoadType.PREPEND->{
                val remoteKeys = getFirstRemoteKey(state)
               val prevKey = remoteKeys?.prevKey ?:MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
            LoadType.APPEND->{
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey ?:MediatorResult.Success(
                    endOfPaginationReached = true
                )
                nextKey
            }
        }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, DogsModel>) : RemoteKeys? {
        return withContext(Dispatchers.IO){
            state.pages
                .firstOrNull { it.data.isNotEmpty() }
                ?.data?.firstOrNull()
                ?.let { dog -> db.remoteKeyDao().getRemoteKeys(dog.id)}
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, DogsModel>) : RemoteKeys? {
        return withContext(Dispatchers.IO){
            state.pages
                .lastOrNull{it.data.isNotEmpty()}
                ?.data?.lastOrNull()
                ?.let { dog -> db.remoteKeyDao().getRemoteKeys(dog.id) }
        }
    }

    private suspend fun getRefreshRemoteKey(state: PagingState<Int, DogsModel>) : RemoteKeys? {
        return withContext(Dispatchers.IO){
            state.anchorPosition?.let { position->
                state.closestItemToPosition(position)?.id?.let {repId->
                    db.remoteKeyDao().getRemoteKeys(repId)
                }
            }
        }
    }

}