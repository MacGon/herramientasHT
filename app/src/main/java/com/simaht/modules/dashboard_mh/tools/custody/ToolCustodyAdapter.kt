package com.simaht.modules.dashboard_mh.tools.custody

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.custody.viewholder.ToolCustodyViewHolder
import com.simaht.utils.SelectableItem

class ToolCustodyAdapter(private var tools: ArrayList<SelectableItem<Tool>>) : RecyclerView.Adapter<ToolCustodyViewHolder>() {

    private var temporalTools: ArrayList<SelectableItem<Tool>> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolCustodyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return ToolCustodyViewHolder(view)
    }

    override fun getItemCount() = tools.size


    override fun onBindViewHolder(holder: ToolCustodyViewHolder, position: Int) {
        holder.bind(tools.get(position),position, CoConstants.ASSIGN.SET_ACTION,true)
    }

}