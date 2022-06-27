package com.hadiid.znnews.util

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {

    private val prefName = "DarkMode.pref" //nama preference
    private var sharedPref: SharedPreferences
    private var editor: SharedPreferences.Editor //melakukan edit

    init {
        sharedPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    //simpan data set
    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }


    //ambil data get true false
    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key,false)
    }


}