package ru.kizapp.yadiskclient.data.prefs

import android.content.SharedPreferences

class PrefsRepoImpl(private val mPreferences: SharedPreferences) : PrefsRepo {

    companion object {
        private const val TOKEN_KEY = "token_key"
    }

    override fun saveToken(token: String) {
        mPreferences.edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    override fun getToken(): String? = mPreferences.getString(TOKEN_KEY, null)

}