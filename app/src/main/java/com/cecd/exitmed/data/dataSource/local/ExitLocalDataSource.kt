package com.cecd.exitmed.data.dataSource.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExitLocalDataSource @Inject constructor(
    @ApplicationContext context: Context
) {
    val masterKeyAlias = MasterKey
        .Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val dataStore = EncryptedSharedPreferences.create(
        context,
        "encrypted_pref_file",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    var isLogin: Boolean
        set(value) = dataStore.edit { putBoolean(IS_LOGIN, value) }
        get() = dataStore.getBoolean(IS_LOGIN, false)

    var accessToken: String
        set(value) = dataStore.edit { putString(ACCESS_TOKEN, value) }
        get() = dataStore.getString(ACCESS_TOKEN, "") ?: ""

    companion object {
        const val IS_LOGIN = "isLogin"
        const val ACCESS_TOKEN = "accessToken"
    }
}
