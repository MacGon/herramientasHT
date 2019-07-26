package com.simaht.modules.dashboard_mh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dashboard_mh.R
import kotlinx.android.synthetic.main.activity_notifications.*

class Notifications : AppCompatActivity() {

    val notificaciones: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        getSupportActionBar()!!.setTitle((Html.fromHtml("<font color=\"#6A6A6A\">" + getString(R.string.msg_title_notificaciones) + "</font>")))


        addReportes()
        rvNotificaciones.layoutManager = LinearLayoutManager(this)
        rvNotificaciones.adapter = NotificacionesAdapter(notificaciones, this)
        rvNotificaciones.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    fun addReportes(){
        notificaciones.add("¡Llegó tu herramienta!\nVamos a registrarla")
        notificaciones.add("¡Tenemos trabajo!\nSe ha incorporado un socio nuevo")
        notificaciones.add("Se vence un folio:234234\n¿Quedó atendido?")
        notificaciones.add("¡Llegó tu herramienta!\nVamos a registrarla")
        notificaciones.add("¡Tenemos trabajo!\nSe ha incorporado un socio nuevo")
        notificaciones.add("Se vence un folio:234545\n¿Quedó atendido?")
        notificaciones.add("¡Llegó tu herramienta!\nVamos a registrarla")
        notificaciones.add("¡Tenemos trabajo!\nSe ha incorporado un socio nuevo")
        notificaciones.add("Se vence un folio:345453\n¿Quedó atendido?")
        notificaciones.add("¡Llegó tu herramienta!\nVamos a registrarla")
        notificaciones.add("¡Tenemos trabajo!\nSe ha incorporado un socio nuevo")
        notificaciones.add("Se vence un folio:645656\n¿Quedó atendido?")
        notificaciones.add("¡Llegó tu herramienta!\nVamos a registrarla")
        notificaciones.add("¡Tenemos trabajo!\nSe ha incorporado un socio nuevo")
        notificaciones.add("Se vence un folio:456677\n¿Quedó atendido?")
        notificaciones.add("¡Llegó tu herramienta!\nVamos a registrarla")
        notificaciones.add("¡Tenemos trabajo!\nSe ha incorporado un socio nuevo")
        notificaciones.add("Se vence un folio:756767\n¿Quedó atendido?")
    }
}