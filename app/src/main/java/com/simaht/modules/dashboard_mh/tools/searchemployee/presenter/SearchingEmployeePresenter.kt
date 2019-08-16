package com.simaht.modules.dashboard_mh.tools.searchemployee.presenter

import com.example.dashboard_mh.BuildConfig
import com.simaht.modules.dashboard_mh.tools.searchemployee.contract.ISearchingEmployeeContract

class SearchingEmployeePresenter(val view : ISearchingEmployeeContract.View): ISearchingEmployeeContract.Presenter {

    override fun getInfoEmployee(employeeNum: Int) {

        if (BuildConfig.DEBUG){
            //TODO a fake response
            view.changeView(false, true)
        } else {
            if (employeeNum.toString().length > 6 || employeeNum.toString().length < 8) { //increese the employee number
                //TODO Consume API
                view.changeView(true, false)
            } else {
                view.showMessage("R.string.badBalidation")
            }
        }
    }

    override fun putEmployeeInfo() {
        view.changeView(false, true)
    }
}