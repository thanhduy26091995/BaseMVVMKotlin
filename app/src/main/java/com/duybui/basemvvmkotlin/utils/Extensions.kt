package com.duybui.basemvvmkotlin.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.duybui.basemvvmkotlin.data.model.Theme


/*Extension functions*/
fun AppCompatActivity.updateForTheme(them: Theme) {
    when (them) {
        Theme.LIGHT -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        Theme.DARK -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
    }
}