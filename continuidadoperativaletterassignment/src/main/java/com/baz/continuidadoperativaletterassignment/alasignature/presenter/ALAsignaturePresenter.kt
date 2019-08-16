package com.baz.continuidadoperativaletterassignment.alasignature.presenter

import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractPresenter
import com.baz.continuidadoperativaletterassignment.alasignature.`interface`.IAsignatureContractView
import com.baz.continuidadoperativaletterassignment.alasignature.model.api.ApiServiceInterfaceAL
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.AssignationToolLA
import com.baz.continuidadoperativaletterassignment.alasignature.model.models.DetailViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ALAsignaturePresenter(val view : IAsignatureContractView) : IAsignatureContractPresenter {

    private val api: ApiServiceInterfaceAL = ApiServiceInterfaceAL.create()
    private val subscriptions = CompositeDisposable()

    override fun loadData() {
/*        var subscribe = api.getPostList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list: List<AssignationToolLA>? ->
                    view.showProgress(false)
                    view.loadDataSuccess(list!!.take(10))
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)*/

    }

    private fun createDetailViewModel(posts: List<AssignationToolLA>): DetailViewModel {
        val postList = posts.take(30)
        return DetailViewModel(postList)
    }

}