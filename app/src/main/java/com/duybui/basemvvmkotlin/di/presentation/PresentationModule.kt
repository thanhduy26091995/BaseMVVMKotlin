package com.duybui.basemvvmkotlin.di.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

import com.duybui.basemvvmkotlin.ui.base.DialogsManager
import com.duybui.basemvvmkotlin.ui.users.UserAdapter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    private lateinit var mActivity: FragmentActivity

    internal val fragmentManager: FragmentManager
        @Provides
        get() = mActivity.supportFragmentManager

    internal val layoutInflater: LayoutInflater
        @Provides
        get() = LayoutInflater.from(mActivity)

    internal val activity: Activity
        @Provides
        get() = mActivity

    constructor(fragmentActivity: FragmentActivity) {
        mActivity = fragmentActivity
    }

    constructor() {}

    @Provides
    internal fun context(activity: Activity): Context {
        return activity
    }

    @Provides
    internal fun dialogsManager(fragmentManager: FragmentManager): DialogsManager {
        return DialogsManager(fragmentManager)
    }

    @Provides
    internal fun userAdapter(activity: Activity): UserAdapter {
        return UserAdapter(activity)
    }

}

