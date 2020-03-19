package com.duybui.basemvvmkotlin.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.duybui.basemvvmkotlin.data.response.BaseResponseObject
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())

        val source = databaseQuery.invoke().map {
            Result.success(it)
        }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == NetworkState.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == NetworkState.FAIL) {
            emit(Result.error<T>(responseStatus.message!!, responseStatus.code))
            emitSource(source)
        }
    }

fun <T> resultLiveDataWithoutLocal(
    networkCall: suspend () -> Result<T>
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading<T>())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == NetworkState.SUCCESS) {
            emit(Result.success(responseStatus.data!!))
        } else if (responseStatus.status == NetworkState.FAIL) {
            emit(Result.error<T>(responseStatus.message!!, responseStatus.code))
        }
    }
