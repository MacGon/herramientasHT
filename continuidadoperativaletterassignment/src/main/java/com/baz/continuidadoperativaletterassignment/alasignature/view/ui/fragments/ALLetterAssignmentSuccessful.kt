package com.baz.continuidadoperativaletterassignment.alasignature.view.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baz.continuidadoperativaletterassignment.R
import com.baz.continuidadoperativaletterassignment.almenu.view.ui.ALetterActivity
import kotlinx.android.synthetic.main.fragment_alletter_asignament_successfull.*

class ALLetterAssignmentSuccessful : Fragment() {

    companion object {
        fun newInstance(): ALLetterAssignmentSuccessful {
            val fragment = ALLetterAssignmentSuccessful()
            //val arg = Bundle().apply { putString("empNameJH", empNameJH)  }
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val param1 = it.getString("empNameJH")
            val param2 = it.getString("empNameJH")
           /* param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val empNameJH: String = arguments?.get("empNameJH").toString()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alletter_asignament_successfull, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val goInitialView: goInitialView = activity as ALetterActivity


        btnGoHome.setOnClickListener{ goInitialView.showInitialView() }
    }

    interface goInitialView {
        fun showInitialView()
    }

}
