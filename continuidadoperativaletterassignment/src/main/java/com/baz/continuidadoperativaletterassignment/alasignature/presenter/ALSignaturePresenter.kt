package com.baz.continuidadoperativaletterassignment.alasignature.presenter

import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.ISignatureContractPresenter
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractView
import com.baz.continuidadoperativaletterassignment.alasignature.model.api.ApiServiceInterfaceAL
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.RequestAssignationToolLA
import com.baz.continuidadoperativaletterassignment.alcommon.ALConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ALSignaturePresenter(val view : IAsignatureContractView) : ISignatureContractPresenter {


    private val api: ApiServiceInterfaceAL = ApiServiceInterfaceAL.create()
    private val subscriptions = CompositeDisposable()

    override fun loadData() {
        view.showProgress(true)
        val request = RequestAssignationToolLA(1, "", "0104000000391", "", "", 0, "", "919610", "", 0, "149766", "GS1301870", "F9FNR528FLMJ", 0, "0000000000000000000F", "", 4)
        val disposable: Disposable = api.getPostList(request).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code == 200) {

                    } else if (response.code == 500) {
                        view.showErrorMessage(ALConstants.MSG_ERROR_ASIGNATURE)
                        view.showProgress(false)
                    }

                }, { error ->
                    view.showErrorMessage(ALConstants.MSG_ERROR_SERVICE)
                    view.showProgress(false)

                })
    }



}