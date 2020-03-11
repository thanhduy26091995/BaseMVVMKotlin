package com.duybui.basemvvmkotlin.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.duybui.basemvvmkotlin.data.model.RedditPost
import com.duybui.basemvvmkotlin.data.network.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class PostsDataSource(val postsRemoteDataSource: PostsRemoteDataSource, val scope: CoroutineScope) :
    PageKeyedDataSource<String, RedditPost>() {

    //TODO
    //https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-supervisor-job.html
    private var supervisorJob = SupervisorJob()
    private val errorMessage = MutableLiveData<String>()


    private fun executeQuery(
        loadSize: Int,
        before: String?,
        after: String?,
        callback: (List<RedditPost>, String?, String?) -> Unit
    ) {

        scope.launch(getJobErrorHandle() + supervisorJob) {
            val data = postsRemoteDataSource.getPosts(loadSize, after, before)
//            val dateFlow = flowOf(postsRemoteDataSource.getPosts(loadSize, after, before))
//            val timeFlow = flowOf(postsRemoteDataSource.getPosts(loadSize, after, before))
//
//            //zip
//            val zippedFlow = dateFlow.zip(timeFlow){date, time ->
//            }
            if (data.status == NetworkState.SUCCESS) {
                val result = data.data
                val listing = result?.data
                val reeditPosts = listing?.children?.map { values ->
                    values.data
                }
                callback(reeditPosts ?: listOf(), listing?.before, listing?.after)
            } else if (data.status == NetworkState.FAIL) {
                errorMessage.postValue(data.message)
            }
        }
    }

    private fun getJobErrorHandle() = CoroutineExceptionHandler { _, error ->
        print(error.message)
    }

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, RedditPost>
    ) {
        executeQuery(params.requestedLoadSize, null, null) { values, before, after ->
            callback.onResult(values, before, after)
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, RedditPost>) {
        executeQuery(params.requestedLoadSize, null, null) { values, _, after ->
            callback.onResult(values, after)
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, RedditPost>
    ) {
        executeQuery(params.requestedLoadSize, null, null) { values, _, after ->
            callback.onResult(values, after)
        }
    }

    fun getErrorMessage(): MutableLiveData<String> =
        errorMessage

    fun saveRedditPosts(redditPost: RedditPost) {
        scope.launch(getJobErrorHandle() + supervisorJob) {
            redditPost
        }
    }

}