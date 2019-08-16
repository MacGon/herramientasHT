package com.simaht.modules.dashboard_mh.tools.toollist.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.toollist.view.viewholder.AddToolViewHolder
import com.simaht.utils.SelectableItem

class ToolListAdapter(var tools: ArrayList<SelectableItem<Tool>>, val selected: (Boolean) -> Unit) : RecyclerView.Adapter<AddToolViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return AddToolViewHolder(view)
    }

    override fun getItemCount() = tools.size

    override fun onBindViewHolder(holder: AddToolViewHolder, position: Int) {
        val tool = tools.get(position)
        tool.selected = true
        holder.bind(tool) { itemDelete, selected ->
            itemDelete?.let {
                tools.remove(it)
                notifyDataSetChanged()
            }
            selected?.let {
                //selected(tools.any { it.selected })
                notifyItemChanged(position)
            }

            if (tools.size > 0)
                selected(tools.any { it.selected })
            else
                selected(false)
        }
    }

    fun addNuewTool(newTool: SelectableItem<Tool>) {
        tools.add(newTool)
        notifyDataSetChanged()
    }

}