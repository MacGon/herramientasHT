package com.simaht

import android.app.Application
import com.simaht.utils.JsonFile

class SIMAHTApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        JsonFile.PATH = this.applicationContext.applicationInfo.dataDir + JsonFile.pathDocuments
    }

}