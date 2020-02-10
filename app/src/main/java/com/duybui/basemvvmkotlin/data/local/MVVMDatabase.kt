package com.duybui.basemvvmkotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duybui.basemvvmkotlin.data.model.RedditPost

@Database(entities = [UserSchema::class, RedditPost::class], version = 1)
abstract class MVVMDatabase : RoomDatabase() {
    abstract fun userDAO(): RoomDAO
}