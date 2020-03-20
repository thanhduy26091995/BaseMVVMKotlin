package com.duybui.basemvvmkotlin.koin

import android.content.Context
import androidx.room.Room
import com.duybui.basemvvmkotlin.data.local.MVVMDatabase
import com.duybui.basemvvmkotlin.data.local.RoomDAO
import com.duybui.basemvvmkotlin.data.local.SharedPrefData
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

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

    fun provideSharedPrefData(context: Context): SharedPrefData {
        return SharedPrefData(context)
    }

    single {
        provideDatabase(androidContext())
    }

    single {
        provideUserDao(get())
    }

    single {
        provideSharedPrefData(get())
    }
}
