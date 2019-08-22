package com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.viewholder

import android.annotation.SuppressLint
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants
import com.example.dashboard_mh.R
import com.simaht.modules.model.ActivoFijo
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*

class FoundDeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("RestrictedApi")
    fun bind(selecTool: SelectableItem<ActivoFijo>, haveAction: Boolean, position: Int, actionSelected: (item: ActivoFijo, selected: Boolean, position: Int) -> Unit) {
        val res = itemView.resources
        with(selecTool) {
            itemView.tvToolMainName.text = item.denominacion //FIXME review wichone is the correct parameter : descMarca
            itemView.tvToolSerianlNumber.text = String.format(res.getString(R.string.msg_serial_number), item.serie)

            if (action != null && haveAction) {
                showActionToDo(this)
            } else if (item.activo) {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_operational)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.grass))
            } else {
                itemView.tvToolStatus.text = "Deteriorada" //fixme define my status plox jajaja
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.colorRed))
            }

            if (action == CoConstants.ACTIONS.CUSTODY) {
                itemView.setOnLongClickListener {
                    selecTool.selected = !selected
                    selecTool.action = null
                    actionSelected(selecTool.item, false, position)
                    true
                }
            }

            itemView.viewSelectec1.visibility = if(selected) View.VISIBLE else View.GONE //Change the background when it's scrolling
            itemView.viewSelectec2.visibility = if(selected) View.VISIBLE else View.GONE

            itemView.ivToolMoreOptions.setOnClickListener {

                val myContext = itemView.context
                val menuBuilder = MenuBuilder(myContext)
                MenuInflater(myContext).inflate(R.menu.actions_menu, menuBuilder)
                menuBuilder.setCallback(object : MenuBuilder.Callback {
                    override fun onMenuItemSelected(menu: MenuBuilder?, item: MenuItem?): Boolean {
                        when (item?.itemId) {
                            R.id.actionCustody -> {
                                selecTool.action = CoConstants.ACTIONS.CUSTODY
                                selecTool.selected = true
                                actionSelected(selecTool.item, true, position)
                            }
                            R.id.actionDamageCharge -> {
                                selecTool.action = CoConstants.ACTIONS.DAMAGE_CHARGE
                                //TODO aply another logic for the next ACTIONS
                                actionSelected(selecTool.item, true, position)
                            }
                            R.id.actionIncident -> {
                                selecTool.action = CoConstants.ACTIONS.INCIDENT
                                actionSelected(selecTool.item, true, position)
                            }
                            R.id.actionFactoryDefect -> {
                                selecTool.action = CoConstants.ACTIONS.FACTORY_DEFECT
                                actionSelected(selecTool.item, true, position)
                            }
                            R.id.actionEndOfUseFulLife -> {
                                selecTool.action = CoConstants.ACTIONS.END_OF_USEFUL_LIFE
                                actionSelected(selecTool.item, true, position)
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

    fun showActionToDo(selecTool: SelectableItem<ActivoFijo>) {
        when (selecTool.action) {
            CoConstants.ACTIONS.CUSTODY -> {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_custody)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.custudy))
            }
            CoConstants.ACTIONS.DAMAGE_CHARGE -> {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_damage_charge)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.damage_charge))
            }
            CoConstants.ACTIONS.INCIDENT -> {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_incident)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.incident))
            }
            CoConstants.ACTIONS.FACTORY_DEFECT -> {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_factory_defect)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.factory_defect))
            }
            CoConstants.ACTIONS.END_OF_USEFUL_LIFE -> {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_end_of_useful_life)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.end_of_useful_life))
            }
        }
    }
}