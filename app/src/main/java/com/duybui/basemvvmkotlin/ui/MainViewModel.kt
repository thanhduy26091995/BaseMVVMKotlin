package com.duybui.basemvvmkotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.duybui.basemvvmkotlin.data.local.SharedPrefData
import com.duybui.basemvvmkotlin.data.model.Theme
import com.duybui.basemvvmkotlin.ui.base.BaseViewModel

class MainViewModel(private val sharedPrefData: SharedPrefData) : BaseViewModel() {

    private val _currentTheme = MutableLiveData<String>()
    val currentTheme: LiveData<String>
        get() = _currentTheme

    init {
        _currentTheme.value =
            sharedPrefData.getString(SharedPrefData.KEY.THEME, Theme.LIGHT.storageKey)
    }

    fun setTheme(theme: Theme) {
        sharedPrefData.setString(SharedPrefData.KEY.THEME, theme.storageKey)
        _currentTheme.value = theme.storageKey
    }
}