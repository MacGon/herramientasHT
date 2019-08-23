package com.baz.continuidadoperativaletterassignment.alasignature.model.models

import com.google.gson.annotations.SerializedName

data class ResponseAssignationLA(
        @SerializedName("codigo") val code: Int? = -1,
        @SerializedName("mensaje") val message: String? = "",
        @SerializedName("salida") val info: ArrayList<OutModel>?,
        @SerializedName("errores") val errors: String?  = ""
)

data class OutModel(
         val name: String? = ""
)