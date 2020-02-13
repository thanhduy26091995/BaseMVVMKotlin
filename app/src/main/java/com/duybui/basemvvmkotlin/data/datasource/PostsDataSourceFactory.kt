package com.duybui.basemvvmkotlin.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.duybui.basemvvmkotlin.data.model.RedditPost
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import kotlinx.coroutines.CoroutineScope

class PostsDataSourceFactory(
    private val postsRemoteDataSource: PostsRemoteDataSource,
    private val scope: CoroutineScope
) :
    DataSource.Factory<String, RedditPost>() {
    private val source = MutableLiveData<PostsDataSource>()

    override fun create(): DataSource<String, RedditPost> {
        val source = PostsDataSource(postsRemoteDataSource = postsRemoteDataSource, scope = scope)
        this.source.postValue(source)
        return source
    }
}