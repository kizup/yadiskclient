package ru.kizapp.yadiskclient.data.prefs

interface PrefsRepo {

    fun saveToken(token: String)

    fun getToken(): String?

}