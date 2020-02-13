package com.duybui.basemvvmkotlin.data.network

import com.duybui.basemvvmkotlin.data.model.RedditApiResponse
import com.duybui.basemvvmkotlin.data.model.User
import com.duybui.basemvvmkotlin.data.response.BaseResponseObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api")
    suspend fun getRandomUser(@Query("results") results: Int): Response<BaseResponseObject<List<User>>>

    @GET("/r/aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 30,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>
}