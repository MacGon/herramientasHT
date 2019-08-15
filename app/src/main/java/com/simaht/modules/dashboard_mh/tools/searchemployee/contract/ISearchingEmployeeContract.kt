package com.simaht.modules.dashboard_mh.tools.searchemployee.contract

interface ISearchingEmployeeContract {

    interface View {
        fun showLoader()
        fun hideLoader()
        fun showMessage(msg: String)
        fun validateEmployeeNumber()
        fun changeView(newEmployee: Boolean, toolsFound: Boolean)
    }

    interface Presenter{
        fun getInfoEmployee(employeeNum : Int)
        fun putEmployeeInfo()
    }
}