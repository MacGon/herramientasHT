package com.simaht.modules.dashboard_mh.tools.custody.contract

import com.simaht.dashboard_mh.AssignTool.Tool

interface ICustodyContract {

    interface View {
        fun showLoader()
        fun hideLoader()
        fun custodyDone()
        fun setEmployee(/*Employee*/)
    }

    interface Presenter {
        fun addToolToCustody(tool: Tool)
        fun applyCustody()
        fun removeToolToCustody(tool: Tool)
        fun haveItemsToCustody(): Boolean
        fun employee()
    }
}