package com.duybui.basemvvmkotlin.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.duybui.basemvvmkotlin.data.model.RedditPost
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import kotlinx.coroutines.CoroutineScope

class PostsDataSourceFactory(
    private val dataRepository: DataRepository,
    private val scope: CoroutineScope
) :
    DataSource.Factory<String, RedditPost>() {
    val source = MutableLiveData<PostsDataSource>()

    override fun create(): DataSource<String, RedditPost> {
        val source = PostsDataSource(dataRepository = dataRepository, scope = scope)
        this.source.postValue(source)
        return source
    }


}