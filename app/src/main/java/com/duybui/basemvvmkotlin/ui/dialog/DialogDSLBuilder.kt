package com.duybui.basemvvmkotlin.ui.dialog

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

data class DialogDSLBuilder(
    val context: Context,
    val layoutId: Int?,
    val setCustomView: (View, DialogFragment) -> Unit
) {

    private constructor(builder: Builder) : this(
        builder.alertContext, builder.layoutInt, builder.setCustomView
    )

    init {
        val frag = MyDialogFragment.newInstance(layoutId)
        frag.setCustomView(setCustomView)
        frag.show(
            (context as FragmentActivity).supportFragmentManager, MyDialogFragment.TAG
        )
    }

    companion object {
        inline fun Activity.dialog(block: Builder.() -> Unit) {
            Builder(this).apply(block).build()
        }

        inline fun Fragment.dialog(block: Builder.() -> Unit) {
            Builder(this.requireActivity()).apply(block).build()
        }
    }

    class Builder(context: Context) {
        var alertContext: Context = context
        var layoutInt: Int? = null
        lateinit var setCustomView: (View, DialogFragment) -> Unit
        fun build() = DialogDSLBuilder(this)

    }
}