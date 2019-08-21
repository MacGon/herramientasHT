package com.simaht.modules.dashboard_mh.tools.custody.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.annotation.SuppressLint
import android.view.MenuInflater
import android.view.MenuItem
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuBuilder.*
import androidx.appcompat.view.menu.MenuPopupHelper
import com.baz.simaht.utils.CoConstants

class ToolCustodyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //TODO REfact
    @SuppressLint("RestrictedApi")
    fun bind(selecTool: SelectableItem<Tool>, position: Int, onView: CoConstants.ASSIGN, haveAction: Boolean, notifyItemSelected:(position: Int, element: Tool, selected: Boolean) -> Unit) {
        with(selecTool) {
            itemView.tvToolMainName.text = item.descTipo
            itemView.tvToolSerianlNumber.text = item.numSerie

            if (action != null && haveAction && onView == CoConstants.ASSIGN.SET_ACTION) {
                showActionToDo(this)
            }

            itemView.ivToolMoreOptions.setOnClickListener {
                val myContext = itemView.context
                val menuBuilder = MenuBuilder(myContext)
                MenuInflater(myContext).inflate(R.menu.actions_menu_option, menuBuilder)
                menuBuilder.setCallback(object : Callback {
                    override fun onMenuItemSelected(menu: MenuBuilder?, item: MenuItem?): Boolean {
                        if (item?.itemId == R.id.optionDelete) {
                            //TODO something
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

            itemView.clItemAssignTool.setOnLongClickListener {
                selected = !selected
                if (selected)
                    notifyItemSelected(position, item, selected)
                else
                    notifyItemSelected(position, item, selected)
                true
            }

            itemView.viewSelectec1.visibility = if(selected) View.VISIBLE else View.GONE //Change the background when it's scrolling
            itemView.viewSelectec2.visibility = if(selected) View.VISIBLE else View.GONE

        }
    }

    fun showActionToDo(selecTool: SelectableItem<Tool>) {
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