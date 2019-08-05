package com.simaht.dashboard_mh.AssignTool.contracts

interface AssignToolChildContractI {

    interface View{
    }

    interface Presenter{
        fun getInstance()
        fun addToolElement()
        fun changeView()
    }

}