package com.simaht.network.remote.services

import com.simaht.modules.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ITools {

    @GET("herramientas/consultaActivoSAP?numEmpleado={employeeNumber}")
    fun consultToolsByEmployee(@Path("employeeNumber") employeeNum: Int) : Call<BaseResponse>

}