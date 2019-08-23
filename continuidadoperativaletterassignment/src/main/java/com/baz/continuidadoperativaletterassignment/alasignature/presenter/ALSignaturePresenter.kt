package com.baz.continuidadoperativaletterassignment.alasignature.presenter

import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.ISignatureContractPresenter
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractView
import com.baz.continuidadoperativaletterassignment.alasignature.model.api.ApiServiceInterfaceAL
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.ALToolAssignment
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.RequestAssignmentToolLA
import com.baz.continuidadoperativaletterassignment.alcommon.ALConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ALSignaturePresenter(val view : IAsignatureContractView) : ISignatureContractPresenter {
    private val api: ApiServiceInterfaceAL = ApiServiceInterfaceAL.create()
    private val subscriptions = CompositeDisposable()

    override fun sendDataAssignment(employeeName:String, employeeNumber:String, employeeDestination:String, tools: ArrayList<ALToolAssignment>) {
        view.showProgress()

        tools.forEach {
            val request = RequestAssignmentToolLA(it.idCategoria, "", it.controlID, "", "", 0, "", employeeNumber, "", 0, employeeDestination, it.numPlaca, it.numSerie, 0, "0000000000000000000F", "", 4)
            if(!dataAssignament(request)) return@forEach
        }

    }

    override fun goActionButtonDeletePaint() {
        view.actionButtonDraw(false)
        view.cleanSignature()
    }

    override fun goDrawSignatureView(views: View, motionEvent: MotionEvent) {
        view.actionButtonDraw(true)
        view.drawSignatureView(views,motionEvent)
    }

    private fun cleanSignatureError(msgError: String){
        view.showErrorMessage(msgError)
        view.cleanSignature()
        view.hideBottomBar()
        view.hideProgress()
        view.actionButtonDraw(false)
    }

    private fun dataAssignament(request: RequestAssignmentToolLA): Boolean {
        var lboolContinue: Boolean = true

        val subscribe = api.getPostList(request).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code == 200) {
                        view.showSuccessFullAsignment()
                        view.hideProgress()
                    } else {
                        cleanSignatureError(ALConstants.MSG_ERROR_SERVICE)
                        lboolContinue = false
                    }
                }, { error ->
                    Log.d("TAG", "Error Service Signature:" + error.message)
                    cleanSignatureError(ALConstants.MSG_ERROR_SERVICE)
                })

        subscriptions.add(subscribe)

        return lboolContinue
    }



}