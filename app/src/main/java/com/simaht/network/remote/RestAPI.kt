package com.simaht.network.remote

import android.util.Log
import com.example.dashboard_mh.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.simaht.modules.model.BaseResponse
import com.google.gson.JsonObject
import com.simaht.network.data.LoginRequestModel
import com.simaht.network.data.LoginResponseModel
import com.simaht.network.data.ModelTest
import com.simaht.network.data.ToolAssignmentResponseModel
import com.simaht.network.remote.services.IAccount
import com.simaht.network.remote.services.ITools
import com.simaht.network.remote.services.IAssigment
import com.simaht.network.remote.services.LoginEndPoint
import com.simaht.network.remote.services.ToolEndPoint
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * This class must be implemented inside a singeton or use like an abstraction layer inside DAGGER
 */
class RestAPI {

    companion object {
        private lateinit var instance : RestAPI
        fun getInstance() = instance
    }

    private val CONNECT_TIMEOUT_MILLIS = 10000L
    private val READ_TIMEOUT_MILLIS = 10000L
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    private var retrorfit : Retrofit
    private val gson: Gson

    /***********************************
     * INTERFACE ENDPOINTS DECLARATION *
     ***********************************/
    private var iAccount: IAccount
    private var iLogin: LoginEndPoint
    private var iToolAssignment: ToolEndPoint
    private val iAssigment: IAssigment
    private val iTools : ITools

    init {
        this.gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()

        retrorfit = retrofitBuilder()
        iAccount = retrorfit.create(IAccount::class.java)
        iAssigment = retrorfit.create(IAssigment::class.java)
        iTools = retrorfit.create(ITools::class.java)
        iLogin = retrorfit.create(LoginEndPoint::class.java)
        iToolAssignment = retrorfit.create(ToolEndPoint::class.java)
        //Add new Interface

    }

    /**
     * This method creates retrofit instance and configures the interceptors.
     */
    fun retrofitBuilder() : Retrofit {
        val clientBuilder = OkHttpClient.Builder()
        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        clientBuilder.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        clientBuilder.addInterceptor(logginInterceptor)

        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build()
    }

    /*************** LOGGIN SERVICE ******************/

    /**
     * This is a test!!
     */
    fun login(request:LoginRequestModel):Single<LoginResponseModel> {
        return iLogin.logIn(gson.fromJson(gson.toJson(request),JsonObject::class.java))
    }

    fun enrollment(serialNum: String, employeeNum: String):Single<LoginResponseModel> {
        return iLogin.getUserInfo(serialNum, employeeNum)
    }

    fun registerUser(request:LoginRequestModel):Single<LoginResponseModel>{
        return iLogin.registerUser(gson.fromJson(gson.toJson(request),JsonObject::class.java))
    }


    /*************** ASSIGMENT TOOL SERVICE ******************/

    fun consultTool(controlNum: String): Single<ToolAssignmentResponseModel> {
        return iToolAssignment.getToolInfo(controlNum)
    }

    /**
     * This is a working test using "dummy" values
     */
    fun readNewTool(callResult: (List<ModelTest>) -> Unit) {

        //val call: Call<Response<List<ModelTest>>> = iAssigment.setNewRegister("0821086558", 4)  /* .apply is a function to replace the enquee */
        iAssigment.setNewRegister("0821086558", 4).apply {
            enqueue(object : Callback<List<ModelTest>> {

                override fun onResponse(call: Call<List<ModelTest>>?, response: Response<List<ModelTest>>?) {
                    Log.i("TEST", response.let { it?.body().toString() })
                    if (response != null && response.code() == 200) { // response different of null and server responce ok (200)
                        response.body()?.let { callResult(it) }
                    } //THIS IS MY CALLABLE ANSWER
                }

                override fun onFailure(call: Call<List<ModelTest>>?, t: Throwable?) {
                    //return a wrong answer
                    //return a message o somethin empty
                    callResult(emptyList()) //it's a empty list, -not null!-
                }

            })
        }
    }




    /*************** Tools INQUIRY SERVICE ******************/

    fun consultTools(employeeNum : Int, callResult: (BaseResponse) -> Unit) {
        iTools.consultToolsByEmployee(employeeNum).apply {
            enqueue(object : Callback<BaseResponse> {

                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    Log.i("TEST", response.let { it.body().toString() })
                    if (response.code() == 200 && response.body()?.code == 200 ) {
                        callResult(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.e("REST_API", "an error has occurred at : ", t)
                    //callResult(kkdkdkd)
                }
            })
        }
    }

}