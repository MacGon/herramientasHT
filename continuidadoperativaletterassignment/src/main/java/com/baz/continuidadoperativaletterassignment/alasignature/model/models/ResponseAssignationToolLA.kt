package com.baz.continuidadoperativaletterassignment.alasignature.model.models

import com.google.gson.annotations.SerializedName

data class ResponseAssignationToolLA(
        @SerializedName("codigo") val code: Int? = -1,
        @SerializedName("mensaje") val message: String? = "",
        @SerializedName("salida") val info: String? = "",
        @SerializedName("errores") val errors: String? = ""
)