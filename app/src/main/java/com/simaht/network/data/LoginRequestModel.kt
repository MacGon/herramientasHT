package com.simaht.network.data

import com.google.gson.annotations.SerializedName

data class EnrollRequestModel(@SerializedName("empleado")var serialNum:String?="",@SerializedName("serie")var employeeNum:String?="")
data class LoginRequestModel(@SerializedName("usuario")var user:String?="",@SerializedName("contrasenia")var password:String?="")