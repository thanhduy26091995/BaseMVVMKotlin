package com.duybui.basemvvmkotlin.data.network


data class Result<out T>(val status: NetworkState, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(NetworkState.SUCCESS, data, null)
        }

        fun <T> error(message: String): Result<T> {
            return Result(NetworkState.FAIL, data = null, message = message)
        }

        fun <T> loading(): Result<T> {
            return Result(NetworkState.RUNNING, data = null, message = null)
        }

    }
}