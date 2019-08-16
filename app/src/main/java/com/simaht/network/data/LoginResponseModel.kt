package com.simaht.network.data

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("codigo") val code: Int? = -1,
    @SerializedName("mensaje") val message: String? = "",
    @SerializedName("salida") val info: OutModel? = OutModel(),
    @SerializedName("errores") val errors: String? = ""
)


data class OutModel(
    @SerializedName("nombre") val name: String? = "",
    @SerializedName("apellidoPat") val lastName: String? = "",
    @SerializedName("apellidoMat") val msLastname: String? = ""
)