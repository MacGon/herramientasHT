package com.simaht.modules.model

import com.google.gson.annotations.SerializedName

data class ActivoFijo(
        @SerializedName("codigo")val code: Int,
        @SerializedName("mensaje")val message: String,
        @SerializedName("errores")val errors: String?,
        @SerializedName("sociedad") val sociedad: String,
        @SerializedName("datoMaestro") val datoMaestro: String,
        @SerializedName("subDatoMaestro") val subDatoMaestro: String,
        @SerializedName("numClaseActivoFijo") val numClaseActivoFijo: String,
        @SerializedName("denominacion") val denominacion: String,
        @SerializedName("serie") val serie: String,
        @SerializedName("placa") val placa: String,
        @SerializedName("fechaAsignacion") val fechaAsignacion: String,
        @SerializedName("fechaCapitaliza") val fechaCapitaliza: String,
        @SerializedName("agrupador") val agrupador: String,
        @SerializedName("idGenerico") val idGenerico: String,
        @SerializedName("ceco") val ceco: String,
        @SerializedName("cecoRespon") val cecoRespon: String,
        @SerializedName("matricula") val matricula: String,
        @SerializedName("numEmpleado") val numEmpleado: String,
        @SerializedName("numMotor") val numMotor: String,
        @SerializedName("descMarca") val descMarca: String,
        @SerializedName("descModelo") val descModelo: String,
        @SerializedName("descEstatus") val descEstatus: String,
        @SerializedName("descUbicacion") val descUbicacion: String,
        @SerializedName("nombreEmpleado") val nombreEmpleado: String,
        @SerializedName("estatusInventario") val estatusInventario: String,
        @SerializedName("empresaId") val empresaId: String,
        @SerializedName("descModelo2") val descModelo2: String,
        @SerializedName("fechaFactura") val fechaFactura: String,
        @SerializedName("fechaInstalacion") val fechaInstalacion: String,
        @SerializedName("fechaEntraMerca") val fechaEntraMerca: String,
        @SerializedName("cecoOperativo") val cecoOperativo: String,

        /**parameters to resturn a custody**/
        var numEmpleadoDestino: String,
        var numEmpleadoOrigen: String,

        /** Parameters not neccesary at the moment of custody**/
        val cecoDestino: String,
        val comentario: String,


        val activo: Boolean = true)