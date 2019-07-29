package com.simaht.utils

import android.content.Context
import android.content.pm.PackageManager

fun getVersion(context: Context): String {
    var versionCode = "v"
    try {
        versionCode += context.packageManager.getPackageInfo(context.packageName, 0).versionName
    } catch (e: PackageManager.NameNotFoundException) {
        //printConsole.e("NameNotFoundException", e.message)
    }

    return versionCode
}

fun getVersionCode(context: Context): String {
    var versionCode = "v"
    try {
        versionCode += context.packageManager.getPackageInfo(context.packageName, 0).versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        //printConsole.e("NameNotFoundException", e.message)
    }

    return versionCode
}