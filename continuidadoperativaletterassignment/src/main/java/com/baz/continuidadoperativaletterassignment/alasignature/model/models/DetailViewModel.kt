package com.baz.continuidadoperativaletterassignment.alasignature.model.models

import com.google.gson.Gson

data class DetailViewModel(val dataAssignation: List<AssignationToolLA>){

    fun toJson(): String {
        return Gson().toJson(this)
    }
}

