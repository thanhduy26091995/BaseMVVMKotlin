package com.duybui.basemvvmkotlin.di.presentation;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.duybui.basemvvmkotlin.data.local.SharedPrefData;
import com.duybui.basemvvmkotlin.data.repo.DataRepository;
import com.duybui.basemvvmkotlin.ui.MainViewModel;
import com.duybui.basemvvmkotlin.ui.base.ViewModelFactory;
import com.duybui.basemvvmkotlin.ui.reddit.RedditViewModel;
import com.duybui.basemvvmkotlin.ui.users.UserViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

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
    @ViewModelKey(RedditViewModel.class)
    ViewModel userViewModel(DataRepository dataRepository) {
        return new RedditViewModel(dataRepository);
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    ViewModel mainViewModel(SharedPrefData sharedPrefData){
        return new MainViewModel(sharedPrefData);
    }
}
