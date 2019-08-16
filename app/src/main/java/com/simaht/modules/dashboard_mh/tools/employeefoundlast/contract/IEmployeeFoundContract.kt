package com.simaht.modules.dashboard_mh.tools.employeefoundlast.contract

import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem

interface IEmployeeFoundContract {

    interface View {
        fun putList()
        fun showLoader()
        fun hideLoader()
        fun addItemFound(toolsFound: List<SelectableItem<Tool>>)
        fun setEmployee(/*Employee*/)
        fun custodyDone()
    }

    interface Presenter {
        fun searchTools()
        fun addTool(): Tool
        fun addToolToCustody(tool: Tool)
        fun removeFromCystody(tool: Tool)
        fun employee()
        fun applyCustody()
        fun haveItemsToCustody(): Boolean
    }
}