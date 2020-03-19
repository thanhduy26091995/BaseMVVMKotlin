package com.duybui.basemvvmkotlin.ui.base

import com.duybui.basemvvmkotlin.data.network.Result
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.success(body)
                }
            }
            return error(response.message(), response.code())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), 404)
        }
    }

    private fun <T> error(message: String, code: Int): Result<T> {
        return Result.error(message, code)
    }
}