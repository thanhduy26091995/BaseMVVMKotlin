package com.duybui.basemvvmkotlin.data.repo

import androidx.lifecycle.MutableLiveData
import com.duybui.basemvvmkotlin.data.local.RoomDAO
import com.duybui.basemvvmkotlin.data.model.RedditApiResponse
import com.duybui.basemvvmkotlin.data.model.User
import com.duybui.basemvvmkotlin.data.network.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DataRepository(val apiInterface: ApiInterface) {
    fun getRandomUser(number: Int): MutableLiveData<List<User>> {
        val data = MutableLiveData<List<User>>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                var userResponse = apiInterface.getRandomUser(number)
                withContext(Dispatchers.Main) {
                    data.value = userResponse.data
                }
            } catch (e: Exception) {
                println(e.toString())
            }
        }
        return data
    }

    suspend fun getPosts(
        loadSize: Int,
        after: String?,
        before: String?
    ): Response<RedditApiResponse> {
        return apiInterface.fetchPosts(loadSize, after, before)
    }
}