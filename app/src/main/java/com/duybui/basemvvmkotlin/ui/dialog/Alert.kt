package com.duybui.basemvvmkotlin.ui.dialog

import android.app.AlertDialog
import android.content.Context

class Alert(
    private val title: String?,
    private val description: String?,
    private val context: Context,
    private val positiveButton: () -> Unit,
    private val negativeButton: () -> Unit
) {

    private constructor(builder: Builder) : this(
        builder.title,
        builder.description, builder.context, builder.positiveButton, builder.negativeButton
    )

    init {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setCancelable(false)
            .setMessage(description)
            .setPositiveButton("OK") { _, _ ->
                positiveButton.invoke()
            }
            .setNegativeButton("Cancel") { _, _ ->
                negativeButton.invoke()
            }.show()
    }

    companion object {
        inline fun alert(block: Builder.() -> Unit) {
            Builder().apply(block).build()
        }
    }

    class Builder {
        var title: String? = null
        var description: String? = null
        lateinit var context: Context
        lateinit var positiveButton: () -> Unit
        lateinit var negativeButton: () -> Unit
        fun build() = Alert(this)
    }
}