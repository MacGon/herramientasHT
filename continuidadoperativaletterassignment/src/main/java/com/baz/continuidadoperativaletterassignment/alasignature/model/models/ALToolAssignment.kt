package com.baz.continuidadoperativaletterassignment.alasignature.model.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ALToolAssignment (
    @SerializedName("controlId") val controlID: String? = "",
    @SerializedName("numSerie") val numSerie: String? = "",
    @SerializedName("numPlaca") val numPlaca: String? = "",
    @SerializedName("idCategoria") val idCategoria: Int? = -1,
    @SerializedName("descCategoria") val descCategoria: String? = "",
    @SerializedName("idTipo") val idTipo: Int? = -1,
    @SerializedName("descTipo") val descTipo: String? = "",
    @SerializedName("numSim") val numSim: String? = ""
): Serializable