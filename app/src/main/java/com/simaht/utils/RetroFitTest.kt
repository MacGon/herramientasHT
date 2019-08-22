package com.simaht.utils

import com.example.dashboard_mh.BuildConfig
import com.simaht.network.remote.services.IAssigment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitTest {

        private var instance: IAssigment? = null

        fun inctance(): IAssigment? {
            if (instance == null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(BuildConfig.URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                instance = retrofit.create(IAssigment::class.java)
            }
            return instance
        }


}