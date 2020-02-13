package com.duybui.basemvvmkotlin.data.datasource

import com.duybui.basemvvmkotlin.data.network.ApiInterface
import com.duybui.basemvvmkotlin.ui.base.BaseDataSource

class PostsRemoteDataSource(val apiInterface: ApiInterface) : BaseDataSource() {
    suspend fun getPosts(loadSize: Int, after: String?, before: String?) = getResult {
        apiInterface.fetchPosts(loadSize, after, before)
    }

    suspend fun getRandomUser(results: Int) = getResult {
        apiInterface.getRandomUser(results)
    }
}