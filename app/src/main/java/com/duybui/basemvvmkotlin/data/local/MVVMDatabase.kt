package com.duybui.basemvvmkotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.duybui.basemvvmkotlin.data.model.Picture
import com.duybui.basemvvmkotlin.data.model.PictureTypeConverters
import com.duybui.basemvvmkotlin.data.model.RedditPost
import com.duybui.basemvvmkotlin.data.model.User

@Database(entities = [Picture::class, User::class, RedditPost::class], version = 1)
@TypeConverters(PictureTypeConverters::class)
abstract class MVVMDatabase : RoomDatabase() {
    abstract fun userDAO(): RoomDAO
}