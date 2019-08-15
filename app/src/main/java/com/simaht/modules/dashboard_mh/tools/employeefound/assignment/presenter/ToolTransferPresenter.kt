package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.presenter

import com.baz.simaht.login.extensions.postDelayed
import com.baz.simaht.utils.CoConstants
import com.baz.simaht.utils.CoConstants.STEP.*
import com.example.dashboard_mh.BuildConfig
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefound.assignment.contracts.IToolTransferContract
import com.simaht.utils.SelectableItem

class ToolTransferPresenter(val view: IToolTransferContract.View) : IToolTransferContract.Presenter {

    private val steps: ArrayList<CoConstants.STEP> = arrayListOf()
    private val STEPS = 7
    private var count = 0
    private var lastCount = 0

    companion object {
        private val UP = 1
        private val DOWN = -1
    }

    override fun setEmployeeNumber() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun validateInformationExistent(employeeNumber: Int) {
        view.showLoader()
        if (BuildConfig.DEBUG) {
            postDelayed(2000) {
                view.putEmployeeData("$employeeNumber - Test Employee")
                view.putToolsFound(arrayListOf(SelectableItem(Tool("PAX Test", "PAX 4000", "TODAY", 124, 4213, "$$36", false, "www.myBill.com")),
                        SelectableItem(Tool("Italika Test", "Moto Italika 220", "TODAY", 421, 21423, "$$2624", true, "www.myITALIK.com"))))
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

        return Tool("Ipad", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")

    }

    override fun toolsFound() {
        postDelayed(2000) {
            val rnd: Int = (1..2).random()
            val toolsfound = arrayListOf<SelectableItem<Tool >>()
            for (i in 0..rnd) {
                toolsfound.add(SelectableItem(addTool()))
            }
            view.addItemFound(toolsfound)
        }
    }

    override fun changeStep(levelUp: Boolean, isNewEmployee: Boolean?) {
        var addToStack = false
        lastCount = count

        if (levelUp) {
            if (isNewEmployee != null)
                count = 5  // this step isnt normal like the others
            else if (count == 4)
                count = 6
            else
                count += UP
            addToStack = true
        } else {
            if (isNewEmployee != null)
                count = lastCount // You are in the step 5 but the previus one is 3, you must put the last count
            else if (count == 6)
                count = 4
            else
                count += DOWN

            steps.remove(steps.last())
        }
        //myCurrentStep()
        currentStep(addToStack)
    }

    /**
     * Notify to the View which one is my curren step
     *
     */
    fun myCurrentStep() {
        view.currentStep(steps.last())
    }

    override fun currentStep(addToStack: Boolean?) {
        when (count) {
            1 -> {
                addToStack?.let {
                    if (addToStack)
                        steps.add(ADDING_TOOL)
                }
                view.showAddingToolView()
            }
            2 -> {
                addToStack?.let {
                    if (addToStack)
                        steps.add(SEARCHING_EMPLOYEE)
                }
                view.showSearchingEmployeeView()
            }
            3 -> {
                addToStack?.let {
                    if (addToStack)
                        steps.add(EMPLOYEE_FOUND)
                }
                view.showEmployeeFoundView()
            }
            4 -> {
                addToStack?.let {
                    if (addToStack)
                        steps.add(CUSTODY)
                }
                view.showActionsView()
            }
            5 -> {
                addToStack?.let {
                    if (addToStack)
                        steps.add(NEW_EMPLOYEE)
                }
                view.showNewEmployeeView()
            }
            6 -> {
                addToStack?.let {
                    if (addToStack)
                        steps.add(DONE)
                }
                view.showProcesDoneView()
            }
            else ->
                view.finishProcess()

        }
        myCurrentStep()
    }

}