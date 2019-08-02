package com.simaht.dashboard_mh.AssignTool.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants.ASSIGN
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*

class AssignToolAdapter(private var tools: ArrayList<SelectableItem<Tool>>, var onView: ASSIGN, val callBack: (Tool?) -> Unit) : RecyclerView.Adapter<AssignToolAdapter.AssignToolViewHolder>() {

    val TAG: String = AssignToolAdapter::class.java.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignToolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return AssignToolViewHolder(view)
    }

    override fun getItemCount() = tools.size

    override fun onBindViewHolder(holder: AssignToolViewHolder, position: Int) {
        holder.bind(tools.get(position), position, callBack)
    }

    fun addNuewTool(newTool: SelectableItem<Tool>) {
        tools.add(newTool)
        notifyItemInserted(itemCount + 1)
        //notifyDataSetChanged()
    }

    fun addNToolsFound(toolFound: ArrayList<SelectableItem<Tool>>) {
        toolFound.forEach {
            tools.add(it)
        }
        notifyDataSetChanged()
    }

    inner class AssignToolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(selecTool: SelectableItem<Tool>, position: Int, callBack: (Tool?) -> Unit) {
            with(selecTool) {
                itemView.tvToolMainName.text = item.name
                itemView.tvToolSerianlNumber.text = item.serialNumber.toString()
                if (item.status) {
                    itemView.tvToolStatus.text = "Disponible" //TODO refact this source
                    itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.grass))
                } else {
                    itemView.tvToolStatus.text = "Agotado"
                    itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.colorRed))
                }

                itemView.clItemAssignTool.setOnLongClickListener {
                    selected = !selected

                    if (selected)
                        callBack(item)
                    else
                        callBack(null)

                    notifyItemChanged(position)

                    true
                }

                itemView.viewSelectec1.visibility = if(selected) View.VISIBLE else View.GONE //Change the background when it's scrolling
                itemView.viewSelectec2.visibility = if(selected) View.VISIBLE else View.GONE

            }


        }
    }

}