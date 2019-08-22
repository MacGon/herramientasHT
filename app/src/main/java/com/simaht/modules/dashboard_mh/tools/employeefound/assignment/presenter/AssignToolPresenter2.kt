package com.simaht.dashboard_mh.AssignTool.presenter

import com.baz.simaht.login.extensions.postDelayed
import com.example.dashboard_mh.BuildConfig
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts.AssignToolContractI
import com.simaht.utils.SelectableItem

class AssignToolPresenter2(val view: AssignToolContractI.View) : AssignToolContractI.Presenter {


    override fun setEmployeeNumber() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun validateInformationExistent(employeeNumber: Int) {
        view.showLoader()
        if (BuildConfig.DEBUG) {
            postDelayed(2000) {
                view.putEmployeeData("$employeeNumber - Test Employee")
                view.putToolsFound(arrayListOf(SelectableItem(Tool("0104000000274", "0821086542", "GS1613202", 1, "MOVILES", 4, "PAX SIM", "0000000000000000000F")),
                        SelectableItem(Tool("0104000000274", "0821086542", "GS1613202", 1, "MOVILES", 4, "PAX SIM", "0000000000000000000F"))))
            }
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
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeView() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addScanedElement(scannedTool: Tool) {
        //Validate scanned Tool
        view.enableAssignationBtn(true)
        //All is ok
        view.addItem(scannedTool)
    }

    override fun addTool(): Tool {
        //TODO this is a fake function
        /*val rnds = (0..4).random()
        return when (rnds) {
            0 -> {
                Tool("Ipad", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")
            }
            1, 2 -> {
                Tool("Casco", "Italika", "17/07/12", 5423, 43843535443, "94733", true, "www.apple.com")
            }
            3 -> {
                Tool("Italika", "Italika", "06/02/08", 633432, 985872832, "9534.38", false, "www.italika.com")
            }
            else -> {
                Tool("Celular", "XAOMI", "17/05/19", 235235, 2352345653, "2489.99", true, "www.xaomi.com")
            }
        }*/

        return Tool("0104000000274", "0821086542", "GS1613202", 1, "MOVILES", 4, "PAX SIM", "0000000000000000000F")

    }

    override fun toolsFound() {
        postDelayed(2000) {
            val rnd: Int = (1..2).random()
            val toolsfound = arrayListOf<SelectableItem<Tool>>()
            for (i in 0..rnd) {
                toolsfound.add(SelectableItem(addTool()))
            }
            view.addItemFound(toolsfound)
        }
    }

}