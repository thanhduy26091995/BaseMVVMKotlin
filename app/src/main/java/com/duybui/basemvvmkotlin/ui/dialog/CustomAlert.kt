package com.duybui.basemvvmkotlin.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View

class CustomAlert(context: Context, layoutId: Int?, setCustomView: (View, AlertDialog) -> Unit) {

    private constructor(builder: Builder) : this(
        builder.alertContext,
        builder.layoutId,
        builder.setCustomView
    )

    init {
        val customView = LayoutInflater.from(context).inflate(layoutId!!, null)
        val dialog = AlertDialog.Builder(context).setCancelable(false).setView(customView).show()
        setCustomView(customView, dialog)
    }

    companion object {
        inline fun Activity.customAlert(block: Builder.() -> Unit) {
            Builder(this).apply(block).build()
        }
    }

    class Builder(context: Context) {
        var alertContext: Context = context
        var layoutId: Int? = null
        lateinit var setCustomView: (View, AlertDialog) -> Unit
        fun build() = CustomAlert(this)
    }
}

data class AlertData(
    val title: String,
    val description: String
)