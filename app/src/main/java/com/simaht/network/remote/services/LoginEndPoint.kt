package com.simaht.network.remote.services

import com.google.gson.JsonObject
import com.simaht.network.data.LoginResponseModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginEndPoint {

    @GET("login/consultarEmpleado/numSerie/{serialNum}/numEmpleado/{employeeNum}")
    fun getUserInfo(@Path("serialNum") serialNum:String ,@Path("employeeNum") employeeNum:String) : Single<LoginResponseModel>

    @POST("login/altaUsuario")
    fun registerUser(@Body json:JsonObject) : Single<LoginResponseModel>

    @POST("login/")
    fun logIn(@Body json:JsonObject) : Single<LoginResponseModel>

}