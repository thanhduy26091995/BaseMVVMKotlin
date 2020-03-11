package com.duybui.basemvvmkotlin.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.duybui.basemvvmkotlin.utils.SingletonHolder


private const val ENCRYPTED_PREFS_FILE_NAME = "default_prefs"


class SharedPrefData private constructor(context: Context) {

    enum class KEY {
        PASSWORD
    }

    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            ENCRYPTED_PREFS_FILE_NAME, MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    init {
        sharedPreferences
    }

    fun setString(key: KEY, value: String) {
        sharedPreferences.edit().putString(key.name, value).apply()
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: KEY): String? {
        return sharedPreferences.getString(key.name, "")
    }

    companion object : SingletonHolder<SharedPrefData, Context>(::SharedPrefData)
}