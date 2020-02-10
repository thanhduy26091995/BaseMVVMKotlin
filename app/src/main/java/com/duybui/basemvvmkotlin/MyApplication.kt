package com.duybui.basemvvmkotlin

import android.app.Application
import com.duybui.basemvvmkotlin.di.application.ApplicationComponent
import com.duybui.basemvvmkotlin.koin.apiModule
import com.duybui.basemvvmkotlin.koin.localDatabaseModule
import com.duybui.basemvvmkotlin.koin.repositoryModule
import com.duybui.basemvvmkotlin.koin.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


class MyApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        //init Dagger 2
//        applicationComponent = DaggerApplicationComponent.builder()
//            .applicationModule(ApplicationModule(this))
//            .apiModule(ApiModule())
//            .build()
//        applicationComponent?.let {
//            it.inject(this)
//        }

        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(apiModule, localDatabaseModule, viewModel, repositoryModule))
        }
        //init custom font
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IBMPlexSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}
