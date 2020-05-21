package com.duybui.basemvvmkotlin.ui.reddit

import com.duybui.basemvvmkotlin.ui.base.BaseFragment
import com.duybui.basemvvmkotlin.utils.arguments

class RedditFragment : BaseFragment() {
    private var mId : Int by arguments()
    private var name : String by arguments()

    companion object {
        fun getInstance(mId: Int, nName: String): RedditFragment =
            RedditFragment().apply {
                this.mId = mId
                this.name = nName
            }
    }
}