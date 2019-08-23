package com.baz.continuidadoperativaletterassignment.alasignature.`interface`

import android.view.MotionEvent
import android.view.View
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.ALToolAssignment

interface ISignatureContractPresenter {

    fun goActionButtonDeletePaint()
    fun goDrawSignatureView(views: View, motionEvent: MotionEvent)
    fun sendDataAssignment(employeeName:String, employeeNumber:String, employeeDestination:String, tools: ArrayList<ALToolAssignment>)
}