package com.duybui.basemvvmkotlin.data.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class User(
    @PrimaryKey
    @SerializedName("email")
    val email: String,
    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    var phone: String,
    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val gender: String,
    @ColumnInfo(name = "picture")
    @SerializedName("picture")
    @TypeConverters(PictureTypeConverters::class)
    val picture: Picture
): Parcelable