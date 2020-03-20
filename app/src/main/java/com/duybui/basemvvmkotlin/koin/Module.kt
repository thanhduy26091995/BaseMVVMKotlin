package com.duybui.basemvvmkotlin.koin

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.duybui.basemvvmkotlin.data.datasource.PostsRemoteDataSource
import com.duybui.basemvvmkotlin.data.local.MVVMDatabase
import com.duybui.basemvvmkotlin.data.local.RoomDAO
import com.duybui.basemvvmkotlin.data.network.ApiInterface
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import com.duybui.basemvvmkotlin.ui.reddit.RedditViewModel
import com.duybui.basemvvmkotlin.ui.users.UserViewModel
import com.duybui.basemvvmkotlin.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    factory { DataRepository(get(), get()) }
}