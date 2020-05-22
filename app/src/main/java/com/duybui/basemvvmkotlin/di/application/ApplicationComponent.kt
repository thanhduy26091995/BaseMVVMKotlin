package com.duybui.basemvvmkotlin.di.application


import android.app.Application
import com.duybui.basemvvmkotlin.di.presentation.PresentationComponent
import com.duybui.basemvvmkotlin.di.presentation.PresentationModule
import com.duybui.basemvvmkotlin.di.service.ServiceComponent
import com.duybui.basemvvmkotlin.di.service.ServiceModule
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ApiModule::class, LocalDatabaseModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent

    fun newServiceComponent(serviceModule: ServiceModule): ServiceComponent

    fun inject(application: Application)
}
