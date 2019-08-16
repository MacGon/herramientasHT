package com.baz.continuidadoperativaletterassignment.alasignature.`interface`
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.AssignationToolLA

interface IAsignatureContractView {

    fun showProgress(show: Boolean)
    fun showErrorMessage(error: String)
    fun loadDataSuccess(list: List<AssignationToolLA>)
}