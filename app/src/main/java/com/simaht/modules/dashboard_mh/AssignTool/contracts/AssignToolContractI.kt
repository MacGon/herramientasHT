package com.simaht.dashboard_mh.AssignTool.contracts

import com.simaht.dashboard_mh.AssignTool.Tool

interface AssignToolContractI {

    interface View{
        fun showLoader()
        fun hideLoader()
        fun putEmployeeData(infoEmployee : String)
        fun putToolsFound(tools: ArrayList<Tool>)
        fun readQR()
        //fun comfirmAssigment()
        fun addItem(scannedTool: Tool)
        fun showMessage(msg : Int)
        fun showMessage(msg : String)
        fun enableAssignationBtn(enable: Boolean)
    }

    interface Presenter{
        fun setEmployeeNumber()
        fun addScanedElement(scannedTool: Tool)
        fun addElement()
        fun validateEmployeeNumber(employeeNumber : String)
        fun openScanner()
        fun assignTools()
        fun changeView()
        //fun toolsComfirmed(List<Tool>)
    }
}