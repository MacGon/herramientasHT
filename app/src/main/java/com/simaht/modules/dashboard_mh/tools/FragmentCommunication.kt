package com.simaht.modules.dashboard_mh.tools

import android.view.View
import androidx.fragment.app.Fragment
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem

interface FragmentCommunication {
    fun addFragment(fragment: Fragment, fragmentTAG : String? = null)
    fun removeFragment()
    fun nextFragment(newEmpl: Boolean? = null, toolsFound: Boolean? = null)
    fun hideKeyboardEvent(view: View)
    fun processDone()
    fun putScannedTools(tools: ArrayList<SelectableItem<Tool>>)
}