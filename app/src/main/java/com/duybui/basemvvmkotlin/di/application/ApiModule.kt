package com.duybui.basemvvmkotlin.di.application


import android.app.Application
import com.duybui.basemvvmkotlin.data.local.RoomDAO
import com.duybui.basemvvmkotlin.data.network.ApiInterface
import com.duybui.basemvvmkotlin.data.repo.DataRepository
import com.duybui.basemvvmkotlin.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(AppConstants.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    internal fun gson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    internal fun okHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    internal fun cache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong()
        val file = File(application.cacheDir, "http-cache")
        return Cache(file, cacheSize)
    }

    @Singleton
    @Provides
    internal fun getAPIInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

//    @Singleton
//    @Provides
//    fun getDataRepository(apiInterface: ApiInterface, roomDAO: RoomDAO): DataRepository {
//        return DataRepository(apiInterface, roomDAO)
//    }

}
