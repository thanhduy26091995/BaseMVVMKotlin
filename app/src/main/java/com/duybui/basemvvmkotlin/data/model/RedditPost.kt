package com.duybui.basemvvmkotlin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class RedditPost (
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val key: String,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,
    @ColumnInfo(name = "score")
    @SerializedName("score")
    val score: Int,
    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author: String,
    @ColumnInfo(name = "num_comments")
    @SerializedName("num_comments")
    val commentCount: Int
)
