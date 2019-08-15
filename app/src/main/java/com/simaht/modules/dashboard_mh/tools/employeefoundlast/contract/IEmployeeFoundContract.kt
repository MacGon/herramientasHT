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
    }

    interface Presenter {
        fun searchTools()
        fun addTool(): Tool
        fun employee()
    }
}