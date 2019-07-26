package com.simaht.modules.dashboard_mh


import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.dashboard_mh.R

class ResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* FORMA PARA CAMBIAR NOMBRE Y COLOR AL TITULO DEL ACTIVITY DE MANERA REGULAR DESDE EL ONCREATE
        this.setTitle(R.string.msg_title_c4_ayuda)
        getSupportActionBar()!!.setTitle((Html.fromHtml("<font color=\"#6A6A6A\">" + getString(R.string.msg_title_notificaciones) + "</font>")))*/

        /* FORMA PARA CAMBIAR NOMBRE Y COLOR AL TITULO DENTRO DE UN FRAGMENTO
        (activity as AppCompatActivity).supportActionBar?.title = "Resultados" */
        (activity as AppCompatActivity).supportActionBar!!.setTitle((Html.fromHtml("<font color=\"#6A6A6A\">" + "Resultados" + "</font>")))

    }


}
