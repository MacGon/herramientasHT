package com.simaht.dashboard_mh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dashboard_mh.R
import kotlinx.android.synthetic.main.activity_c4_help.*


class C4Help : AppCompatActivity() {

    val socios: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c4_help)
        getSupportActionBar()!!.setTitle((Html.fromHtml("<font color=\"#6A6A6A\">" + getString(R.string.msg_title_c4_ayuda) + "</font>")));

        addReportes()
        rvC4Reportes.layoutManager = LinearLayoutManager(this)
        rvC4Reportes.adapter = C4Adapter(socios, this)
        rvC4Reportes.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    fun addReportes(){
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 13123")
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 12352")
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 54565")
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 76576")
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 75631")
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 23454")
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 67567")
        socios.add("¡Un socio sufrió un accidente!\nFolio C4: 23424")
    }
}
