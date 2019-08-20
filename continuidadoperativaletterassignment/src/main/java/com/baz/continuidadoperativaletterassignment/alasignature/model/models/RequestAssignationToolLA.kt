package com.baz.continuidadoperativaletterassignment.alasignature.model.models

import com.google.gson.annotations.SerializedName

data class RequestAssignationToolLA(@SerializedName("categoria")val categoria: Int? = 0,
                                    @SerializedName( "cecoDestino")val cecoDestino: String? = "",
                                    @SerializedName( "codigoControl")  val codigoControl:String? = "",
                                    @SerializedName( "datoMaestro") val datoMaestro:String? = "",
                                    @SerializedName( "empresaId") val empresaId: String? = "",
                                    @SerializedName( "estatus") val estatus:Int? = 0,
                                    @SerializedName( "imei") val imei: String? = "",
                                    @SerializedName( "jefeHTA") val jefeHTA: String? = "",
                                    @SerializedName( "mac") val mac:String? =  "",
                                    @SerializedName( "modelo") val modelo: Int? = 0,
                                    @SerializedName( "numEmpleadoDestino") val numEmpleadoDestino:String? = "",
                                    @SerializedName( "numeroPlaca") val numeroPlaca: String? ="",
                                    @SerializedName( "numeroSerie") val numeroSerie:String?="",
                                    @SerializedName( "operatividad") val operatividad:Int?= 0,
                                    @SerializedName( "sim") val sim:String? = "",
                                    @SerializedName( "sociedad") val sociedad:String? = "",
                                    @SerializedName( "tipoAF") val tipoAF : Int? = 0)

/*
{
    "categoria": 1,
    "cecoDestino": "",
    "codigoControl": "0104000000391",
    "datoMaestro": "",
    "empresaId": "",
    "estatus": 0,
    "imei": "",
    "jefeHTA": "919610",
    "mac": "",
    "modelo": 0,
    "numEmpleadoDestino": "149766",
    "numeroPlaca": "GS1301870",
    "numeroSerie": "F9FNR528FLMJ",
    "operatividad": 0,
    "sim": "0000000000000000000F",
    "sociedad": "",
    "tipoAF": 4
}*/
