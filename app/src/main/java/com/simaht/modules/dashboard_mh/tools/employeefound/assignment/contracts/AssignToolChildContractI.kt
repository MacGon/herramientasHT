package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts

interface AssignToolChildContractI {

    interface View{
        fun validateContinue()
    }

    interface Presenter{
        fun getInstance()
        fun addToolElement()
        fun changeView()
    }

}