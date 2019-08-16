package com.simaht.network.data

import com.google.gson.annotations.SerializedName

data class ToolAssignmentResponseModel(
    @SerializedName("codigo") val code: Int? = -1,
    @SerializedName("mensaje") val message: String? = "",
    @SerializedName("salida")private  val _info: ArrayList<OutModelTool> = ArrayList(),
    @SerializedName("errores") val errors: String? = ""
){
    val info:OutModelTool
    get()= _info[0]
}


data class OutModelTool(
    @SerializedName("controlId") val controlID: String? = "",
    @SerializedName("numSerie") val numSerie: String? = "",
    @SerializedName("numPlaca") val numPlaca: String? = "",
    @SerializedName("idCategoria") val idCategoria: Int? = -1,
    @SerializedName("descCategoria") val descCategoria: String? = "",
    @SerializedName("idTipo") val idTipo: Int? = -1,
    @SerializedName("descTipo") val descTipo: String? = "",
    @SerializedName("numSim") val numSim: String? = ""
)