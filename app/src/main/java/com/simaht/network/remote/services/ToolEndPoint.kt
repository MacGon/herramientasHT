package com.simaht.network.remote.services

import com.simaht.network.data.ToolAssignmentResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ToolEndPoint {

    @GET("herramientasMoviles/transferenciaActivos/consultarDetalleHerramienta/numControl/{controlNum}")
    fun getToolInfo(@Path("controlNum") controlNum:String) : Single<ToolAssignmentResponseModel>

}