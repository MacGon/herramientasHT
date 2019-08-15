package com.simaht.modules.dashboard_mh.tools.toollist.view.viewholder

import android.annotation.SuppressLint
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard_mh.R
import com.simaht.dashboard_mh.AssignTool.Tool
import com.simaht.utils.SelectableItem
import kotlinx.android.synthetic.main.item_assign_tool_confirmation.view.*
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuBuilder.*
import androidx.appcompat.view.menu.MenuPopupHelper

class AddToolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //TODO REfact
    @SuppressLint("RestrictedApi")
    fun bind(selecTool: SelectableItem<Tool>, options:(delete : Boolean) -> Unit) {
        with(selecTool) {
            itemView.tvToolMainName.text = item.name
            itemView.tvToolSerianlNumber.text = item.serialNumber.toString()

            if (item.status) {
                itemView.tvToolStatus.text = itemView.resources.getString(R.string.msg_operational)
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.grass))
            } else {
                itemView.tvToolStatus.text = "Robada" //fixme define my status plox jajaja
                itemView.tvToolStatus.setTextColor(itemView.resources.getColor(R.color.colorRed))
            }

            itemView.ivToolMoreOptions.setOnClickListener {

                val myContext = itemView.context
                val menuBuilder = MenuBuilder(myContext)
                MenuInflater(myContext).inflate(R.menu.basic_options, menuBuilder)
                menuBuilder.setCallback(object : Callback {
                    override fun onMenuItemSelected(menu: MenuBuilder?, item: MenuItem?): Boolean {
                        when  (item?.itemId) {
                            R.id.optionDelete -> {
                                options(true)
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



}