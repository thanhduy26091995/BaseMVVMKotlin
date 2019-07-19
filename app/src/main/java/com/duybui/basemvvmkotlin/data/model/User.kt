package com.duybui.basemvvmkotlin.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("picture")
    val picture: Picture
)