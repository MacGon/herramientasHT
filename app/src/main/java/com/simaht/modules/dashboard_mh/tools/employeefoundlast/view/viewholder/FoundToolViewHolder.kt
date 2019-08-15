package com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.viewholder

import android.view.MenuInflater
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*
import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.appcompat.view.menu.MenuBuilder.*
import com.baz.simaht.utils.CoConstants.ACTIONS.*

@SuppressLint("RestrictedApi")
class FoundToolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(selecTool: SelectableItem<Tool>, haveAction: Boolean, position: Int , actionSelected: (Boolean, Int) -> Unit) {
        with(selecTool) {
            itemView.tvToolMainName.text = item.name
            itemView.tvToolSerianlNumber.text = item.serialNumber.toString()

            if (action != null && haveAction) {
                showActionToDo(this)
            } else if (item.status) {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_operational)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.grass))
            } else {
                itemView.tvToolStatus.text = "Robada" //fixme define my status plox jajaja
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.colorRed))
            }

            itemView.ivToolMoreOptions.setOnClickListener {

                val myContext = itemView.context
                val menuBuilder = MenuBuilder(myContext)
                MenuInflater(myContext).inflate(R.menu.actions_menu, menuBuilder)
                menuBuilder.setCallback(object : Callback {
                    override fun onMenuItemSelected(menu: MenuBuilder?, item: MenuItem?): Boolean {
                        when (item?.itemId) {
                            R.id.actionCustody -> {
                                selecTool.action = CUSTODY
                                actionSelected(true, position)
                            }
                            R.id.actionDamageCharge -> {
                                selecTool.action = DAMAGE_CHARGE
                                //TODO aply another logic for the next ACTIONS
                                actionSelected(true, position)
                            }
                            R.id.actionIncident -> {
                                selecTool.action = INCIDENT
                                actionSelected(true, position)
                            }
                            R.id.actionFactoryDefect -> {
                                selecTool.action = FACTORY_DEFECT
                                actionSelected(true, position)
                            }
                            R.id.actionEndOfUseFulLife -> {
                                selecTool.action = END_OF_USEFUL_LIFE
                                actionSelected(true, position)
                            }
                        }
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