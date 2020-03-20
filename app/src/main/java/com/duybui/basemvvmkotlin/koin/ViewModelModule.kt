package com.duybui.basemvvmkotlin.koin

import com.duybui.basemvvmkotlin.ui.MainViewModel
import com.duybui.basemvvmkotlin.ui.reddit.RedditViewModel
import com.duybui.basemvvmkotlin.ui.users.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    single { UserViewModel(get()) }
    single { MainViewModel(get()) }
    viewModel {
        RedditViewModel(get())
    }
}