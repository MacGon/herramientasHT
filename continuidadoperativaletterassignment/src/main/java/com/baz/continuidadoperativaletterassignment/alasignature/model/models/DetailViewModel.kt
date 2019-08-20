package com.baz.continuidadoperativaletterassignment.alasignature.model.models

import com.google.gson.Gson

data class DetailViewModel(val dataRequestAssignation: List<RequestAssignationToolLA>){

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

