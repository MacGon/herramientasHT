package com.simaht.modules.dashboard_mh.tools.employeefoundlast.presenter

import com.baz.simaht.login.extensions.postDelayed
import com.baz.simaht.utils.CoConstants
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.contract.IEmployeeFoundContract
import com.simaht.utils.SelectableItem

class EmployeeFoundPresenter(val view : IEmployeeFoundContract.View): IEmployeeFoundContract.Presenter {

    private var toolCustody = arrayListOf<Tool>()

    override fun employee() {
        //create employee from singleton

        view.setEmployee(/*Employee*/)

    }

    override fun addToolToCustody(tool: Tool) {
        toolCustody.add(tool)
    }

    override fun removeFromCystody(tool: Tool) {
        toolCustody.remove(tool)
    }

    override fun searchTools() {
        view.showLoader()
        postDelayed(500) {
            //val rnd: Int = (1..2).random()
            val toolsfound = arrayListOf<SelectableItem<Tool>>()
            //for (i in 0..rnd) {
                toolsfound.add(SelectableItem(Tool("Ipad", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")))
                toolsfound.add(SelectableItem(Tool("Impresora", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")))
                toolsfound.add(SelectableItem(Tool("Casco", "Apple", "16/03/15", 8435, 9431242, "873.43", true, "www.google.com")))
            //}

            view.addItemFound(toolsfound)
            view.hideLoader()
        }
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

    override fun applyCustody() {
        view.showLoader()

        if (haveItemsToCustody()) {
            //Rest API
            //TODO send toolList
            view.hideLoader()
            view.custodyDone()
        } else {
            //TODO -- Could I need this validation??
        }
    }

    override fun haveItemsToCustody() = toolCustody.isNotEmpty()

}