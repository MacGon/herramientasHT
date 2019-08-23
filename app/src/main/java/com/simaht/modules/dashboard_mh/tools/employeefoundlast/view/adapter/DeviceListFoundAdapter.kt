package com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baz.simaht.utils.CoConstants
import com.example.dashboard_mh.R
import com.simaht.modules.dashboard_mh.tools.employeefoundlast.view.viewholder.FoundDeviceViewHolder
import com.simaht.modules.model.ActivoFijo
import com.simaht.utils.SelectableItem

class DeviceListFoundAdapter(private var tools: ArrayList<SelectableItem<ActivoFijo>>, var haveAction: Boolean, val haveSomeSelection: (Boolean, item: ActivoFijo) -> Unit) : RecyclerView.Adapter<FoundDeviceViewHolder>() {

    private var temporalTools: ArrayList<SelectableItem<ActivoFijo>> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundDeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_assign_tool_confirmation, parent, false)
        return FoundDeviceViewHolder(view)
    }

    override fun getItemCount() = tools.size


    override fun onBindViewHolder(holder: FoundDeviceViewHolder, position: Int) {
        val activoFijo = tools.get(position)
        holder.bind( activoFijo, haveAction, position) { item, anActionSelected, pos ->
            anActionSelected.apply {
                notifyItemChanged(pos)
            }
            haveSomeSelection(anActionSelected, item)
        }
    }

    fun addToolsFound(toolsFound : List<SelectableItem<ActivoFijo>>) {
        toolsFound.forEach {
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

    fun listToCustody(): ArrayList<SelectableItem<ActivoFijo>> {
        val custodyList = arrayListOf<SelectableItem<ActivoFijo>>()
        tools.filter { it.action != null}.forEach { custodyList.add(it) }
        return custodyList
    }

    fun haveCustody() = tools.any{it.selected}

    fun errorCustody() {
        temporalTools.forEach {
            it.action = null
            it.selected = false
        }
        notifyDataSetChanged()
    }

}