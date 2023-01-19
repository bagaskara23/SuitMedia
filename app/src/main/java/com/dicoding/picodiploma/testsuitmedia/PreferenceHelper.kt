package com.dicoding.picodiploma.testsuitmedia

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private val prefName = "DatabaseSuitmedia"
    private val sharedPref : SharedPreferences
    val editor : SharedPreferences.Editor

    init{
        sharedPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE )
        editor = sharedPref.edit()
    }

    fun put(key: String, value: String){
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun put(key: String, value: Boolean){
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun clear(){
        editor.clear()
            .apply()
    }

    companion object{
        const val NAME = "NAME"
        const val CHOOSE_NAME = "CHOOSE_NAME"
    }
}