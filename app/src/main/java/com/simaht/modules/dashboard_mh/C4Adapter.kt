package com.simaht.modules.dashboard_mh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard_mh.R
import kotlinx.android.synthetic.main.c4_list_item.view.*

class C4Adapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.c4_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvReportesType.text = items.get(position)
    }

}

class ViewHolder (view: View): RecyclerView.ViewHolder (view) {
    val tvReportesType = view.tvC4List
}
