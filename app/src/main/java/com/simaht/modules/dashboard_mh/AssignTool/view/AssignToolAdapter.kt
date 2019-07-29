package com.simaht.dashboard_mh.AssignTool.view

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants.ASSIGN
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*

class AssignToolAdapter(private var tools : ArrayList<Tool>, val onView : ASSIGN) : RecyclerView.Adapter<AssignToolAdapter.AssignToolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignToolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return AssignToolViewHolder(view)
    }

    override fun getItemCount() = tools.size

    override fun onBindViewHolder(holder: AssignToolViewHolder, position: Int) {

        holder.bind(tools.get(position).name)

        when (onView) {
            ASSIGN.ASSIGMENT -> {
                //holder.viewType(onView)
                holder.bind(tools.get(position).name)
            }
            ASSIGN.COMFIRMATION -> {
                holder.bind(tools.get(position).name)
            }
        }
    }


    fun addNuewTool(newTool : Tool) {
        tools.add(newTool)
        notifyDataSetChanged()
    }


    inner class AssignToolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(name: String) {
            with(itemView) {
                itemView.tvElementTitleAT.text = name
            }
        }

        fun viewType(assign: ASSIGN) {
            when(assign) {
                ASSIGN.ASSIGMENT -> {
                    itemView.cbComfirmToolAT.visibility = View.VISIBLE
                }
                ASSIGN.COMFIRMATION -> {
                    itemView.cvItemAssign.setCardBackgroundColor(itemView.context.resources.getColor(R.color.transparentGray)) //= itemView.context.resources.getColor(R.color.colorAccent)
                }
            }
        }

    }

}