package com.simaht.modules.dashboard_mh.tools.employeefound.assignment.view

import android.annotation.SuppressLint
import android.view.*
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuBuilder.*
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants
import com.baz.simaht.utils.CoConstants.ACTIONS.*
import com.baz.simaht.utils.CoConstants.ASSIGN.ASSIGMENT
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*


class AssignToolAdapter(private var tools: ArrayList<SelectableItem<Tool>>, var onView: CoConstants.ASSIGN = ASSIGMENT, var haveAction: Boolean, val callBack: (Boolean) -> Unit) : RecyclerView.Adapter<AssignToolAdapter.AssignToolViewHolder>() {

    private val TAG: String = AssignToolAdapter::class.java.name
    private var temporalTools: ArrayList<SelectableItem<Tool>> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignToolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return AssignToolViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (haveAction) {
            tools.count { it.action != null }
        } else {
            if (!tools.isEmpty())
                callBack(haveCustody())
            tools.size
        }
    }

    override fun onBindViewHolder(holder: AssignToolViewHolder, position: Int) {
        holder.bind(tools.get(position), position, onView, callBack)
    }

    fun addNuewTool(newTool: SelectableItem<Tool>) {
        tools.add(newTool)
        if (itemCount == 0)
            notifyItemInserted(itemCount)
        else
            notifyItemInserted(itemCount + 1)
        notifyDataSetChanged()
    }

    fun addToolsFound(toolFound: List<SelectableItem<Tool>>) {
        toolFound.forEach {
            tools.add(it)
            temporalTools.add(it)
        }
        notifyDataSetChanged()
    }

    fun removeTemporalTools() {
        if (!temporalTools.isEmpty()) {
            temporalTools.forEach {
                tools.remove(it)
            }
            temporalTools = arrayListOf()
        }
    }

    fun removeTool(tool: SelectableItem<Tool>) {
        tools.remove(tool)
        notifyDataSetChanged()
    }

    fun chanveView(view: CoConstants.ASSIGN) {
        onView = view
        notifyDataSetChanged()
    }

    fun applyCustudy() {
        //TODO FIX by only CUSTODY action (acctualy this is a test!!)
        val ipad = "Ipad"
        tools.filter { !it.selected } // = 0
//        tools.forEach {
//            if (it.item.name.equals(ipad)) {
//                tools.remove(it)
//            }
//        }
        notifyDataSetChanged()
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

    inner class AssignToolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("RestrictedApi")
        fun bind(selecTool: SelectableItem<Tool>, position: Int, onView: CoConstants.ASSIGN, callBack: (Boolean) -> Unit) {
            with(selecTool) {
                itemView.tvToolMainName.text = item.name
                itemView.tvToolSerianlNumber.text = item.serialNumber.toString()

                if (action != null && haveAction && onView == CoConstants.ASSIGN.SET_ACTION) {
                    showActionToDo(this)
                } else {
                    if (item.status) {
                        itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_operational)
                        itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.grass))
                    } else {
                        itemView.tvToolStatus.text = "Robada" //fixme define my status plox jajaja
                        itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.colorRed))
                    }
                }
                itemView.clItemAssignTool.setOnLongClickListener {
                    selected = !selected
                    //if (selected)
                        //callBack(item)
                    //else
                        //callBack(null)
                    notifyItemChanged(position)
                    true
                }

                itemView.viewSelectec1.visibility = if (selected) View.VISIBLE else View.GONE //Change the background when it's scrolling
                itemView.viewSelectec2.visibility = if (selected) View.VISIBLE else View.GONE

                itemView.ivToolMoreOptions.setOnClickListener {

                    val myContext = itemView.context
                    val menuBuilder = MenuBuilder(myContext)
                    MenuInflater(myContext).inflate(R.menu.actions_menu, menuBuilder)
                    menuBuilder.setCallback(object : Callback {
                        override fun onMenuItemSelected(menu: MenuBuilder?, item: MenuItem?): Boolean {
                            when  (item?.itemId) {
                                R.id.actionCustody -> {
                                    selecTool.action = CUSTODY
                                }
                                R.id.actionDamageCharge -> {
                                    selecTool.action = DAMAGE_CHARGE
                                }
                                R.id.actionIncident -> {
                                    selecTool.action = INCIDENT
                                }
                                R.id.actionFactoryDefect -> {
                                    selecTool.action = FACTORY_DEFECT
                                }
                                R.id.actionEndOfUseFulLife -> {
                                    selecTool.action = END_OF_USEFUL_LIFE
                                }
                            }

                            callBack(true)//    set enabled btn coninue filtering
                            notifyItemChanged(position)
                            return false
                        }

                        override fun onMenuModeChange(menu: MenuBuilder?) {
                        }
                    })

                    val menuHelper = MenuPopupHelper(myContext, menuBuilder, itemView.ivToolMoreOptions)
                    menuHelper.setForceShowIcon(true)
                    menuHelper.show()
                }
            }
        }

        fun showActionToDo(selecTool: SelectableItem<Tool>) {
            when (selecTool.action) {
                CUSTODY -> {
                    itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_custody)
                    itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.custudy))
                }
                DAMAGE_CHARGE -> {
                    itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_damage_charge)
                    itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.damage_charge))
                }
                INCIDENT -> {
                    itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_incident)
                    itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.incident))
                }
                FACTORY_DEFECT -> {
                    itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_factory_defect)
                    itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.factory_defect))
                }
                END_OF_USEFUL_LIFE -> {
                    itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_end_of_useful_life)
                    itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.end_of_useful_life))
                }
            }
        }
    }

}