package com.simaht

import com.example.dashboard_mh.BuildConfig

object SIMAHTSingleton {

    private var statusCompile: Boolean? = null

    fun instance(): Boolean? {

        if (statusCompile == null) { //Singleton Double Check Looking
            statusCompile = BuildConfig.IS_DEBUD
        }

        return statusCompile
    }

    fun isDebug() = statusCompile!!

}