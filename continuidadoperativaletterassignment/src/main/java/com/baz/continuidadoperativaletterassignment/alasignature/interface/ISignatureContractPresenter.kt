package com.baz.continuidadoperativaletterassignment.alasignature.`interface`

import android.view.MotionEvent
import android.view.View

interface ISignatureContractPresenter {

    fun goActionButtonDeletePaint()
    fun goDrawSignatureView(views: View, motionEvent: MotionEvent)
    fun sendDataAssignment()
}