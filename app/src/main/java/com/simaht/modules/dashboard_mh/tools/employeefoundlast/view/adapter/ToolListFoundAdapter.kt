package com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.viewholder.FoundToolViewHolder
import com.simaht.utils.SelectableItem

class ToolListFoundAdapter(private var tools: ArrayList<SelectableItem<Tool>>, var haveAction: Boolean) : RecyclerView.Adapter<FoundToolViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundToolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return FoundToolViewHolder(view)
    }

    override fun getItemCount() = tools.size


    override fun onBindViewHolder(holder: FoundToolViewHolder, position: Int) {
        holder.bind(tools.get(position))
    }


    fun applyCustudy() {
        //TODO FIX by only CUSTODY action (acctualy this is a test!!)
        tools.filter { !it.selected } // = 0
    }

    fun haveCustody(): Boolean {
        var needCustody = false
        val ipad = "Ipad"
        tools.forEach {
            if (it.item.name.equals(ipad)) {
                needCustody = true
                //it.selected = needCustody
            }
        }
        return needCustody
    }

    fun setAction(action: CoConstants.ACTIONS) {
        tools.filter { it.selected }.forEach { it.action = action }
        haveAction = true
        notifyDataSetChanged()

    }
}