package com.duybui.basemvvmkotlin.di.presentation;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import com.duybui.basemvvmkotlin.ui.base.ViewModelFactory;
import com.duybui.basemvvmkotlin.ui.users.UserViewModel;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

import javax.inject.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    ViewModel userViewModel(Application application) {
        return new UserViewModel(application);
    }
}
