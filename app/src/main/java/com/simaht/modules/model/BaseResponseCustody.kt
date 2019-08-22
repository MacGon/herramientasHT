package com.simaht.modules.model

import com.google.gson.annotations.SerializedName

class BaseResponseCustody(
        @SerializedName("codigo")val code: Int,
        @SerializedName("mensaje")val message: String,
        @SerializedName("errores")val errors: List<String>?,
        @SerializedName("salida")val output: List<String?>?)