package com.duybui.basemvvmkotlin.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duybui.basemvvmkotlin.data.model.User

@Dao
interface RoomDAO {
    @Query("Select * from User")
    fun getUserList(): LiveData<List<User>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<User>)
}