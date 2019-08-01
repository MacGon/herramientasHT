package com.simaht.network.remote

import android.util.Log
import com.example.dashboard_mh.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.simaht.network.remote.services.IAccount
import com.simaht.network.remote.services.IAssigment
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestAPI {

    companion object {
        private lateinit var instance : RestAPI
        fun getInstance() = instance
    }

    private val CONNECT_TIMEOUT_MILLIS = 35000L
    private val READ_TIMEOUT_MILLIS = 35000L
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    private var retrorfit : Retrofit
    private val gson: Gson

    /***********************************
     * INTERFACE ENDPOINTS DECLARATION *
     ***********************************/
    private var iAccount: IAccount
    private var iAssigment: IAssigment

    init {
        this.gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()

        //instance = RestAPI()

        retrorfit = retrofitBuilder()

        iAccount = retrorfit.create(IAccount::class.java)
        iAssigment = retrorfit.create(IAssigment::class.java)
    }

    /**
     * This method creates retrofit instance and configures the interceptors.
     */
    fun retrofitBuilder() : Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        //val logginInterceptor = HttpLoggingInterceptor()
        //l

        clientBuilder.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        //clientBuilder.addInterceptor { logg }

        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //  implementation 'com.jakewharton.rxbinding3:rxbinding-material:3.0.0-alpha2'
                .client(clientBuilder.build())
                .build()
    }

    /*************** LOGGIN SERVICE ******************/

    /**
     * This is a test!!
     */
    fun login() {
        //TODO( call the methods declared inside Interface to do an action, like login, I forget my password, etc ) --IT MUST HAVE A CALLABLE TO RETURN A RESPONSE AT ANY TIME!!--
        //iAccount.doSomething
    }


    /*************** ASSIGMENT TOOL SERVICE ******************/

    /**
     * This is a working test using "dummy" values
     */
    fun readNewTool(callResult: (List<ModelTest>) -> Unit) {

        //val call: Call<Response<List<ModelTest>>> = iAssigment.setNewRegister("0821086558", 4)
        val call: Call<List<ModelTest>> = iAssigment.setNewRegister("0821086558", 4).apply {
            enqueue(object: Callback<List<ModelTest>> {

                override fun onResponse(call: Call<List<ModelTest>>?, response: Response<List<ModelTest>>?) {
                    Log.i("TEST",response.let { it?.body().toString() })
                    callResult(response!!.body()) //THIS IS MY CALLABLE ANSWER
                }
                override fun onFailure(call: Call<List<ModelTest>>?, t: Throwable?) {
                    //return a wrong answer
                }

            })
        }
    }


}