package com.duybui.basemvvmkotlin.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.duybui.basemvvmkotlin.data.model.Theme
import kotlin.properties.ReadWriteProperty


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

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

fun <T : Any> arguments(): ReadWriteProperty<Fragment, T> = FragmentBundleDataDelegate()