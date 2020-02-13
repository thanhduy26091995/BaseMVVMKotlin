package com.duybui.basemvvmkotlin.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PictureTypeConverters {
    @TypeConverter
    fun stringToSomeObjectList(data: String): Picture {
        var gson = Gson()
        val listType = object : TypeToken<Picture>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(picture: Picture): String {
        var gson = Gson()
        return gson.toJson(picture)
    }
}