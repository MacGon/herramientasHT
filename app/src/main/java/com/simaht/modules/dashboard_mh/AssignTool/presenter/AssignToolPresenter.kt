package com.simaht.dashboard_mh.AssignTool.presenter

import com.example.dashboard_mh.BuildConfig
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.dashboard_mh.AssignTool.contracts.AssignToolContractI
import com.simaht.utils.SelectableItem

class AssignToolPresenter(val view: AssignToolContractI.View) : AssignToolContractI.Presenter {


    override fun setEmployeeNumber() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun validateInformationExistent(employeeNumber: Int) {
        view.showLoader()
        if (BuildConfig.DEBUG) {
            //postDelayed(2000) {
                view.putEmployeeData("$employeeNumber - Test Employee")
                view.putToolsFound(arrayListOf(SelectableItem(Tool("0104000000274", "0821086542", "GS1613202", 1, "MOVILES", 4, "PAX SIM", "0000000000000000000F")),
                        SelectableItem(Tool("0104000000274", "0821086542", "GS1613202", 1, "MOVILES", 4, "PAX SIM", "0000000000000000000F"))))
            //}
        } else {
            //TODO Implement the service response
        }
    }

    override fun addElement() {
        view.readQR()
    }

    override fun validateEmployeeNumber(employeeNumber: String) {
        if (employeeNumber.isNotEmpty()) {
            //TODO sent to validate : employeeNumber.toInt()
            validateInformationExistent(employeeNumber.toInt())
        } else {
            view.showMessage(R.string.msg_empty_employee)
        }
    }

    override fun openScanner() {
        view.readQR()
    }

    override fun assignTools() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addScanedElement(scannedTool: Tool) {
        //Validate scanned Tool
        view.enableAssignationBtn(true)
        //All is ok
        view.addItem(scannedTool)
    }

}