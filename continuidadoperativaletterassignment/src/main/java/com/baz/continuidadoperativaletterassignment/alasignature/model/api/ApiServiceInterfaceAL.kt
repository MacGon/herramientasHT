package com.baz.continuidadoperativaletterassignment.alasignature.model.api
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.RequestAssignationToolLA
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.ResponseAssignationLA
import com.baz.continuidadoperativaletterassignment.alcommon.ALConstants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServiceInterfaceAL {

    @POST("herramientasMoviles/transferenciaActivos/asignaPax?")
    fun getPostList(@Body json: RequestAssignationToolLA): Observable<ResponseAssignationLA>


    companion object Factory {
        fun create(): ApiServiceInterfaceAL {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ALConstants.URL_SPRING_DESARROLLO)
                    .build()

            return retrofit.create(ApiServiceInterfaceAL::class.java)
        }
    }
}