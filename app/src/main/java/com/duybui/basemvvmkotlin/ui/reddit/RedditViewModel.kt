package com.duybui.basemvvmkotlin.ui.reddit

import com.duybui.basemvvmkotlin.data.repo.DataRepository
import com.duybui.basemvvmkotlin.ui.base.BaseViewModel

class RedditViewModel(val dataRepository: DataRepository) : BaseViewModel() {
    var connectivityAvailable: Boolean = false

    val posts = dataRepository.observePosts(connectivityAvailable, ioScope)
}