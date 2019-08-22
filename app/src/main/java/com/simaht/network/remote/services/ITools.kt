package com.simaht.network.remote.services

import com.simaht.modules.model.BaseResponse
import com.simaht.modules.model.BaseResponseCustody
import com.simaht.modules.model.Custody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ITools {

    @GET("herramientasMoviles/transferenciaActivos/consultaActivoSAP")
    fun consultToolsByEmployee(@Query("numEmpleado") employeeNum: Int) : Call<BaseResponse>


    @POST("herramientasMoviles/transferenciaActivos/asignaEnCustodiaOEnEstatus")
    fun toolsToCustody(@Body custody: Custody): Call<BaseResponseCustody>

}