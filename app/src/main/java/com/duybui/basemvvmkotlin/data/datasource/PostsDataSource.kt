package com.duybui.basemvvmkotlin.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.duybui.basemvvmkotlin.data.model.RedditPost
import com.duybui.basemvvmkotlin.data.network.NetworkState
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import kotlinx.coroutines.*

class PostsDataSource(val dataRepository: DataRepository, val scope: CoroutineScope) :
    PageKeyedDataSource<String, RedditPost>() {

    //TODO
    //https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-supervisor-job.html
    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()


    private fun executeQuery(
        loadSize: Int,
        before: String?,
        after: String?,
        callback: (List<RedditPost>, String?, String?) -> Unit
    ) {
        networkState.postValue(NetworkState.RUNNING)
        scope.launch(getJobErrorHandle() + supervisorJob) {
            val data = dataRepository.getPosts(loadSize, after, before)
            networkState.postValue(NetworkState.SUCCESS)
            if (data.isSuccessful) {
                val listing = data.body()?.data
                val reeditPosts = listing?.children?.map { values ->
                    values.data
                }
                callback(reeditPosts ?: listOf(), listing?.before, listing?.after)
            }
        }
    }

    private fun getJobErrorHandle() = CoroutineExceptionHandler { _, _ ->
        networkState.value = NetworkState.FAIL
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

    fun getNetworkState(): LiveData<NetworkState> =
        networkState

    fun saveRedditPosts(redditPost: RedditPost){
        scope.launch(getJobErrorHandle() + supervisorJob){
            redditPost
        }
    }

}