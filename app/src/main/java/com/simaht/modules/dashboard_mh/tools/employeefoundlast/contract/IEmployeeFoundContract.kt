package com.simaht.modules.dashboard_mh.tools.employeefoundlast.contract

import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.model.ActivoFijo
import com.simaht.utils.SelectableItem

interface IEmployeeFoundContract {

    interface View {
        fun putList()
        fun showLoader()
        fun hideLoader()
        fun addItemFound(toolsFoundTest: ArrayList<SelectableItem<Tool>>? = null, devicesFound: ArrayList<SelectableItem<ActivoFijo>>? = null)
        fun setEmployee(haveCustody: Boolean, name: String)
        fun custodyDone()
        fun wrongCustody()
        fun showMessage(msg: String)
        fun inespectedError()
    }

    interface Presenter {
        fun searchTools(employee: Int)
        fun addTool(): Tool
        fun addToolToCustodyTest(tool: Tool)
        fun addToolToCustody(tool: ActivoFijo)
        fun removeFromCystodyTest(tool: Tool)
        fun removeFromCystody(tool: ActivoFijo)
        fun applyCustody()
        fun haveItemsToCustody(): Boolean
    }
}