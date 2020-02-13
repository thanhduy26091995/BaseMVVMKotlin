package com.duybui.basemvvmkotlin.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.duybui.basemvvmkotlin.data.datasource.PostsDataSourceFactory
import com.duybui.basemvvmkotlin.data.datasource.PostsRemoteDataSource
import com.duybui.basemvvmkotlin.data.local.RoomDAO
import com.duybui.basemvvmkotlin.data.model.RedditPost
import com.duybui.basemvvmkotlin.data.network.resultLiveDataWithoutLocal
import com.duybui.basemvvmkotlin.utils.pagedListConfig
import kotlinx.coroutines.CoroutineScope

class DataRepository(val postsRemoteDataSource: PostsRemoteDataSource, val roomDAO: RoomDAO) {

    fun observePosts(isConnectivityAvailable: Boolean, coroutineScope: CoroutineScope) =
        observeRemotePosts(coroutineScope)

    private fun observeRemotePosts(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<RedditPost>> {
        val postsDataSourceFactory =
            PostsDataSourceFactory(
                postsRemoteDataSource = postsRemoteDataSource,
                scope = ioCoroutineScope
            )
        return LivePagedListBuilder(
            postsDataSourceFactory,
            pagedListConfig()
        ).build()
    }

    fun getUsers(results: Int) =
        resultLiveDataWithoutLocal(networkCall = { postsRemoteDataSource.getRandomUser(results) })

}