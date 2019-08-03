package com.simaht.network.remote.services

import retrofit2.Call
import com.simaht.network.data.ModelTest
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST



interface IAssigment {

    /**
     * * THIS ENDPOINT IS A TEST *
     *
     * In this case, it has a dynamic route, completed with the following parameters
     * @param serialNum it's a String come from device used
     * @param toolTipe type of tool it's scanning
     */
    @GET("herramientasDeTrabajo/api-codigos/consultarCodigo/numSerie/{serialNum}/tipoHerramienta/{toolType}")
    fun setNewRegister(@Path("serialNum") serialNum : String, @Path("toolType") toolTipe : Int): Call<List<ModelTest>>


    /**
     * * THIS ENDPOINT IS A TEST *
     *
     * In this case the function is sending a BodyRequest like a JSON, we could suppose a json like this: \"{ newTools:[tool:"NEW PAX", tool:"ITALIC TOOL", tool: "HELMET"], employeeNumber: 001035, date:"31/08/2019 16:33:12}\"
     * @param newAssigment is the object whos is waiting for our web service
     */
    @POST("test/newToolAssigned")
    fun sendToolToAssignet(@Body newAssigment: ModelTest) // ModelTest could be a type wherever we want

    /**
     *
     * Add a new function ++
     *
     *
     */
}