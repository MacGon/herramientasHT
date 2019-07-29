package com.simaht.dashboard_mh.AssignTool.contracts

import com.simaht.dashboard_mh.AssignTool.Tool

interface AssignToolContractI {

    interface View{
        fun showLoader()
        fun hideLoader()
        fun putEmployeeData()
        fun putToolsFound(tools: ArrayList<Tool>)
        fun readQR()
        //fun comfirmAssigment()
        fun addItem(scannedTool: Tool)
    }

    interface Presenter{
        fun setEmployeeNumber()
        fun askForTools()
        fun addScanedElement(scannedTool: Tool)
        fun addElement()
        fun openScanner()
        fun assignTools()
        fun changeView()
    }
}