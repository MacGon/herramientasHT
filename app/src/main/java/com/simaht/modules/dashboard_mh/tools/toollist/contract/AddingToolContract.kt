package com.simaht.modules.dashboard_mh.tools.toollist.contract

import com.simaht.dashboard_mh.AssignTool.Tool

interface AddingToolContract {

    interface View{
        fun readQR()
        fun addItem(scannedTool: Tool)
        fun showMessage(msgInt: Int? = null, msgStr: String? = null)
        fun enableAssignationBtn(enable: Boolean)
        fun onMessageError(error: String)
        fun progressDialogShow()
        fun progressDialogHide()
    }

    interface Presenter{
        fun addScanedElement(scannedTool: Tool)
        fun addElement()
        fun openScanner()
        fun assignTools()
        fun addTool(): Tool
        fun getToolInfo(controlNum: String)
    }
}