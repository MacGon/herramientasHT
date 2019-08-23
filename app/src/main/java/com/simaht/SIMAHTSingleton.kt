package com.simaht

import com.example.dashboard_mh.BuildConfig

object SIMAHTSingleton {

    private var statusCompile: Boolean? = null
    @Volatile
    var numJH: Int = 0
        set(value) { field = value }

    fun instance(): Boolean? {

        if (statusCompile == null) { //Singleton Double Check Looking
            statusCompile = BuildConfig.IS_DEBUD
        }

        return statusCompile
    }

    fun isDebug() = statusCompile!!

}