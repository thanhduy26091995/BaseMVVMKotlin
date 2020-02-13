package com.duybui.basemvvmkotlin.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDAO {
    @Query("Select * from UserSchema")
    fun getUserList(): List<UserSchema>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<UserSchema>)
}