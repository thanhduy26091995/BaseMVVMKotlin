package com.duybui.basemvvmkotlin.ui.reddit

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.duybui.basemvvmkotlin.data.datasource.PostsDataSourceFactory
import com.duybui.basemvvmkotlin.data.network.NetworkState
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import com.duybui.basemvvmkotlin.ui.base.BaseViewModel
import com.duybui.basemvvmkotlin.utils.pagedListConfig

class RedditViewModel(val dataRepository: DataRepository) : BaseViewModel() {
    private val postsDataSourceFactory =
        PostsDataSourceFactory(dataRepository = dataRepository, scope = ioScope)

    val posts = LivePagedListBuilder(postsDataSourceFactory, pagedListConfig()).build()
    val networkState: LiveData<NetworkState>? =
        Transformations.switchMap(postsDataSourceFactory.source) { it.getNetworkState() }
}