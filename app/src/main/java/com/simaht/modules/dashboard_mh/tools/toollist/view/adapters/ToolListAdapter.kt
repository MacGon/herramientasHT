package com.simaht.modules.dashboard_mh.tools.toollist.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.modules.dashboard_mh.tools.toollist.view.viewholder.AddToolViewHolder
import com.simaht.utils.SelectableItem

class ToolListAdapter(var tools: ArrayList<SelectableItem<Tool>>, val haveElements: (Boolean) -> Unit) : RecyclerView.Adapter<AddToolViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return AddToolViewHolder(view)
    }

    override fun getItemCount() : Int {
        haveElements(tools.size > 0)
        return tools.size
    }

    override fun onBindViewHolder(holder: AddToolViewHolder, position: Int) {
        val tool = tools.get(position)
        holder.bind(tools.get(position)/*, options*/) {
            if (it) {
                tools.remove(tool)
                notifyItemChanged(position)
            }
        }
    }

    fun addNuewTool(newTool: SelectableItem<Tool>) {
        tools.add(newTool)
        notifyDataSetChanged()
    }

}