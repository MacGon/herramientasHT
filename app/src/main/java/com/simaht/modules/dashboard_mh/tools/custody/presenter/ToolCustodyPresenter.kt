package com.simaht.modules.dashboard_mh.tools.custody.presenter

import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.custody.contract.ICustodyContract

class ToolCustodyPresenter(val view: ICustodyContract.View): ICustodyContract.Presenter {

    private var toolCustody = arrayListOf<Tool>()

    override fun addToolToCustody(tool: Tool) {
        toolCustody.add(tool)
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

    override fun removeToolToCustody(tool: Tool) {
        toolCustody.remove(tool)
    }

    override fun haveItemsToCustody() = toolCustody.isNotEmpty()

    override fun employee() {
        //create employee from singleton

        view.setEmployee(/*Employee*/)
    }

}