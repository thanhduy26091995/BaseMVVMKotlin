package com.duybui.basemvvmkotlin.ui.reddit

import androidx.recyclerview.widget.DiffUtil
import com.duybui.basemvvmkotlin.data.model.RedditPost

class DiffUtilCallBack : DiffUtil.ItemCallback<RedditPost>() {
    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.title == newItem.title
                && oldItem.score == newItem.score
                && oldItem.commentCount == newItem.commentCount
    }

}