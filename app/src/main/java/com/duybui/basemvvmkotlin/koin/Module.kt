package com.duybui.basemvvmkotlin.koin

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.duybui.basemvvmkotlin.data.local.MVVMDatabase
import com.duybui.basemvvmkotlin.data.local.RoomDAO
import com.duybui.basemvvmkotlin.data.network.ApiInterface
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import com.duybui.basemvvmkotlin.ui.base.BaseActivity
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

val apiModule = module {
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(AppConstants.BASE_URL)
            .build()
    }

    fun gson(): Gson {
        return GsonBuilder().create()
    }

    fun okHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

    fun cache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val file = File(application.cacheDir, "http-cache")
        return Cache(file, cacheSize)
    }

    fun getAPIInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    single {
        provideRetrofit(get(), get())
    }

    single {
        gson()
    }

    single {
        okHttpClient(get())
    }

    single {
        cache(androidApplication())
    }

    single {
        getAPIInterface(get())
    }
}

val repositoryModule = module {
    factory { DataRepository(get()) }
}

val localDatabaseModule = module {
    fun provideDatabase(context: Context): MVVMDatabase {
        return Room.databaseBuilder(
            context,
            MVVMDatabase::class.java,
            MVVMDatabase::class.java.simpleName
        ).build()
    }

    fun provideUserDao(mvvmDatabase: MVVMDatabase): RoomDAO {
        return mvvmDatabase.userDAO()
    }

    single {
        provideDatabase(androidContext())
    }

    single {
        provideUserDao(get())
    }
}

val viewModel = module {
    single { UserViewModel(get()) }

    viewModel {
        RedditViewModel(get())
    }
}