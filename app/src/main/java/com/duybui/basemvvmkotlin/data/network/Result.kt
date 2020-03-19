package com.duybui.basemvvmkotlin.data.network


data class Result<out T>(
    val status: NetworkState,
    val data: T?,
    val message: String?,
    val code: Int
) {
    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(NetworkState.SUCCESS, data, null, 200)
        }

        fun <T> error(message: String, code: Int): Result<T> {
            return Result(NetworkState.FAIL, data = null, message = message, code = code)
        }

        fun <T> loading(): Result<T> {
            return Result(NetworkState.RUNNING, data = null, message = null, code = -1)
        }

    }
}