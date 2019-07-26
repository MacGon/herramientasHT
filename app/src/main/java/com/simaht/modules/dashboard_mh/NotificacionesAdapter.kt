package com.simaht.modules.dashboard_mh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dashboard_mh.R
import kotlinx.android.synthetic.main.notifications_list_item.view.*

class NotificacionesAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolderNotification>() {

    override fun onBindViewHolder(holder: ViewHolderNotification, position: Int) {
        holder.tvNotificacionesType.text = items.get(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNotification {
        return ViewHolderNotification(LayoutInflater.from(context).inflate(R.layout.notifications_list_item, parent, false))
    }
}

class ViewHolderNotification (view: View): RecyclerView.ViewHolder (view) {
    val tvNotificacionesType = view.tvNotificaciones
}
