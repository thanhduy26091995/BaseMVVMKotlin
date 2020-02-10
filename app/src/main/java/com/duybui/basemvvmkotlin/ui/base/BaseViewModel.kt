package com.duybui.basemvvmkotlin.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BaseViewModel() : ViewModel() {
    protected val mainScope = CoroutineScope(Dispatchers.Main)

    protected val ioScope = CoroutineScope(Dispatchers.IO)


    override fun onCleared() {
        super.onCleared()
        mainScope.coroutineContext.cancel()
        ioScope.coroutineContext.cancel()
    }
}
