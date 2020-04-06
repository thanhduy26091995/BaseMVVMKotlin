package com.duybui.basemvvmkotlin.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.duybui.basemvvmkotlin.data.model.Theme


/*Extension functions*/
fun AppCompatActivity.updateForTheme(them: Theme) {
    when (them) {
        Theme.LIGHT -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        Theme.DARK -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        else -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
    }
}

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }
