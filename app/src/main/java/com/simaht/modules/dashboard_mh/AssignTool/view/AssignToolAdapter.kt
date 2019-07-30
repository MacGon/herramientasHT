package com.simaht.dashboard_mh.AssignTool.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants.ASSIGN
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*

class AssignToolAdapter(private var tools: ArrayList<Tool>, var onView: ASSIGN) : RecyclerView.Adapter<AssignToolAdapter.AssignToolViewHolder>() {

    val TAG: String = AssignToolAdapter::class.java.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignToolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return AssignToolViewHolder(view)
    }

    override fun getItemCount() = tools.size

    override fun onBindViewHolder(holder: AssignToolViewHolder, position: Int) {
        tools.get(position).name = "${tools.get(position).name} ${position + 1}"
        holder.bind("${tools.get(position).name}")

        when (onView) {
            ASSIGN.ASSIGMENT -> {
                holder.viewType(onView)
                holder.bind(tools.get(position).name)
            }
            ASSIGN.COMFIRMATION -> {
                holder.viewType(onView)
                holder.bind(tools.get(position).name)
            }
        }
    }


    fun addNuewTool(newTool: Tool) {
        tools.add(newTool)
        notifyDataSetChanged()
    }

    fun addNToolsFound(toolFound: ArrayList<Tool>) {
        toolFound.forEach {
            tools.add(it)
        }
        notifyDataSetChanged()
    }

//    fun getItemsConfirmd(): List<Tool> {
//        tools.forEach {
//            if (it.status) {  // its ton
//
//            }
//
//        }

        inner class AssignToolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(name: String) {
                with(itemView) {
                    itemView.tvElementTitleAT.text = name
                }
            }

            fun viewType(assign: ASSIGN) {
                when (assign) {
                    ASSIGN.ASSIGMENT -> {
                        itemView.cbComfirmToolAT.visibility = View.GONE
                    }
                    ASSIGN.COMFIRMATION -> {
                        itemView.cvItemAssign.setCardBackgroundColor(itemView.context.resources.getColor(R.color.transparentGray)) //= itemView.context.resources.getColor(R.color.colorAccent)
                        itemView.cbComfirmToolAT.visibility = View.VISIBLE
                        if (itemView.cbComfirmToolAT.isChecked) {
                            Log.i(TAG, " Im checking this item to assign...")
                        }
                    }
                }
            }

        }

    }