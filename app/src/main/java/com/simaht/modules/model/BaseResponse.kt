package com.simaht.modules.model

import com.google.gson.annotations.SerializedName

class BaseResponse (
        @SerializedName("codigo")val code: Int,
        @SerializedName("mensaje")val message: String,
        @SerializedName("errores")val errors: String?,
        @SerializedName("salida")val output: List<ActivoFijo>)
