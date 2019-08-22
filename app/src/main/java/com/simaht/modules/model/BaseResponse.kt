package com.simaht.modules.model

import com.google.gson.annotations.SerializedName

data class BaseResponse (
        @SerializedName("codigo")val code: Int,
        @SerializedName("mensaje")val message: String,
        @SerializedName("errores")val errors: List<String>?,
        @SerializedName("salida")val output: List<ActivoFijo>?)
