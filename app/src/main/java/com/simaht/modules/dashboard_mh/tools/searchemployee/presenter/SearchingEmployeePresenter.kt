package com.simaht.modules.dashboard_mh.tools.searchemployee.presenter

import com.example.dashboard_mh.BuildConfig
import com.simaht.modules.dashboard_mh.tools.searchemployee.contract.ISearchingEmployeeContract

class SearchingEmployeePresenter(val view : ISearchingEmployeeContract.View): ISearchingEmployeeContract.Presenter {

    override fun getInfoEmployee(employeeNum: Int) { //FIXME what happen when Int vlaue is 004235

        if (BuildConfig.DEBUG){
            //TODO a fake response
            view.changeView(false, true, employeeNum)
        } else {
            if (employeeNum.toString().length > 6 || employeeNum.toString().length < 8) { //increese the employee number
                //TODO Consume API
                view.changeView(true, false, employeeNum)
            } else {
                view.showMessage("Número de usuario Invaludo") //FIXME add inside strings
            }
        }
    }
}