package com.baz.continuidadoperativaletterassignment.alasignature.`interface`

import android.view.MotionEvent
import android.view.View

interface IAsignatureContractView {
   fun cleanSignature()
   fun hideBottomBar()
   fun showSuccessFullAsignment()
   fun showProgress()
   fun hideProgress()
   fun showErrorMessage(error: String)
   fun actionButtonDraw(enabled: Boolean)
   fun drawSignatureView(view: View, motionEvent: MotionEvent)


   /*fun loadDataSuccess(list: List<ResponseAssignationToolLA>)*/

}