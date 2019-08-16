package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts

import com.baz.simaht.utils.CoConstants.STEP
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem

interface IToolTransferContract {

    interface View{
        fun showLoader()
        fun hideLoader()
        fun putEmployeeData(infoEmployee : String)
        fun putToolsFound(tools: ArrayList<SelectableItem<Tool>>)
        fun readQR()
        //fun comfirmAssigment()
        fun addItem(scannedTool: Tool)
        fun addItemFound(toolFound: List<SelectableItem<Tool>>)
        fun showMessage(msgInt: Int? = null, msgStr: String? = null)
        fun enableAssignationBtn(enable: Boolean)
        fun currentStep(step: STEP)

        fun showAddingToolView()
        fun showSearchingEmployeeView()
        fun showEmployeeFoundView()
        fun showActionsView()
        fun showNewEmployeeView()
        fun showProcesDoneView()
        fun finishProcess()

    }

    interface Presenter{
        fun setEmployeeNumber()
        fun addScanedElement(scannedTool: Tool)
        fun addElement()
        fun validateEmployeeNumber(employeeNumber : String)
        fun openScanner()
        fun assignTools()
        fun changeView()
        fun currentStep(addToStack:Boolean? = null)
        fun changeStep(levelUp: Boolean, isNewEmployee: Boolean? = null)

        //TODO FAKE functions
        fun addTool(): Tool
        fun toolsFound()
        //fun toolsComfirmed(List<Tool>)
    }
}