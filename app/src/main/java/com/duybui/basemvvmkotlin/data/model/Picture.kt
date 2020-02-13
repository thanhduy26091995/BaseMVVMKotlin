package com.duybui.basemvvmkotlin.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
@TypeConverters(PictureTypeConverters::class)
data class Picture(
    @PrimaryKey
    @ColumnInfo(name = "large")
    @SerializedName("large")
    val large: String
): Parcelable